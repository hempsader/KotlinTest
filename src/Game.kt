import java.lang.Exception
import java.lang.IllegalStateException
import kotlin.streams.toList

fun main() {
    Game.play()
}



object Game {
    private val player = Player("Madrigal")
    private var isRunning = true
    private var currentRoom = Room("TownSquare")
    init {
        println("Greetings traveler")
        player.castFireball()
    }
    fun play(){
        while(isRunning)
        {
            println(currentRoom.description())
            println(currentRoom.load())
            print("Add a command: ")
            println("Last command: ${GameInput(readLine()).processComand()}")
        }
    }
    private fun printPlayerStatus(player: Player){
        println("Aura: ${player.auraColor()}")
        println("Blessed: ${if (player.isBlessed) "YES" else "NO"}")
        println(player.name + player.formatHealthStatus())
    }

    private var worldMap = listOf(
         listOf(Room("Tavern"), Room("Back Room"), Room("Town Square")),
         listOf(Room("Long Corridor"), Room("Generic Room")))

    private fun move(directionInput: String) {
        try {
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPos = direction.updateCoord(player.currentPosition)
            if(!newPos.inBounds)
            {
                throw IllegalStateException("No such direction")
            }
            val newRoom = worldMap[newPos.y][newPos.x]
            player.currentPosition = newPos
            currentRoom = newRoom
            "Ok you move to direction $direction to the ${currentRoom.name}.\n ${newRoom.load()}"
        }catch (e: Exception){
            "Invalid direction input"
        }
    }
    private fun quit(quitInput: String){
        try {
            val quitCommand = quitInput.toLowerCase().trim() == "quit"
            if(quitCommand){
                isRunning = false
                println("Farewall little hero....")
            }
        }catch (e: Exception){
            "Invalid direction input"
        }
    }

    private fun printLogic(
            auraColor: String,
            isBlessed: Boolean,
            name: String,
            healthStatus: String
    ) {
        println(
                "(Aura: $auraColor) " +
                        "(Blessed: ${if (isBlessed) "YES" else "NO"})"
        )
        println("$name $healthStatus")

    }

    private fun map(mapInput: String){
        if(mapInput.toLowerCase().trim() == "map"){
            val valueFirstRow = mutableListOf<String>()
            val valueSecondRow = mutableListOf<String>()
            worldMap[0].forEach {
                valueFirstRow += "0"
            }
            worldMap[1].forEach {
                valueSecondRow += "0"
            }
            val finalMap = listOf(valueFirstRow,valueSecondRow).toMutableList()


            finalMap[player.currentPosition.y][player.currentPosition.x] = "X"

            finalMap.forEach {
                it.forEach {
                    print(it)
                }
                println()
            }
        }
    }

    private fun figh() = currentRoom.monster?.let {
        while (player.healthPoints > 0 && it.healthPoints > 0){
            slay(it)
            Thread.sleep(1000)
        }
        "Battle complete"
    } ?: "Nothing to fight"

    private fun slay(monster: Monster){
        println("${monster.name} did ${monster.attack(player)} damage! - ${monster.name} health: ${monster.healthPoints}")
        println("${player.name} did ${player.attack(monster)} damage! - ${player.name} health: ${player.healthPoints}")

        if(player.healthPoints <=0 ){
            println("You have been defeaded")
        }
        if(monster.healthPoints <= 0)
        {
            println("${monster.name} have been defeaded")
        }

        println("${player.name} did ${player.damageRoll}")
        println("${monster.name} did ${monster.damageRoll}")
    }

    private class GameInput(arg: String?){
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1) { "" }
        fun commandNotFound() = "I am not sure what you are trying to do..."
        fun processComand() = when(command.toLowerCase()){
            "move" -> move(argument)
            "quit" -> quit(command)
            "map" ->map(command)
            "fight" -> figh()
            else -> commandNotFound()
        }
    }
}






