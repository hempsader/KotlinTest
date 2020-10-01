import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"
var playerGold = 10
var playerSilver = 9
var dragonsBreathQuantity: Double = 5.0
val lastName = mutableListOf("Eli", "Mordoc", "Sophie")
val firstNames = listOf("Sam","Frodo","Gandalf")
var patrons = mutableSetOf<String>()
val menuItems = File("src/data/menu_data.txt").readText().split("\n")
val maxLength = menuItems.maxOf {
    val drinkName = it.split(',')
    return@maxOf (drinkName[1]+drinkName[2]).length+14
}
val patronGold = mutableMapOf<String,Double>()


fun main() {

  //  printMenu()
    (1..20).forEach {
        patrons.add("${lastName.shuffled().last()} ${firstNames.shuffled().last()}")
    }



}
fun placeOrder(patronName: String,menuData: String) {

    val indexAphostrope = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexAphostrope)
    println("$patronName it's speaking with tavern master $tavernMaster to place an order")

    val data = menuData.split(',')
    val (name, type, price) = data
    val message = "$patronName buys a $name ($type) for price $price"
    println(message)
    performPurchase(price.toDouble())
    val phrase = if (type == "Dragon's Breath") {
        toDragonSpeak("Ah, this drink is good").toUpperCase()
    }else {
        "Thank you for the $name"
    }
    println(phrase)
}
private fun toDragonSpeak(phrase: String): String =
        phrase.replace(Regex("[aeiou]")){
            when(it.value){
                "a" -> "4"
                "e" -> "3"
                "i" -> "1"
                "o" -> "0"
                "u" -> "|_|"
                else -> it.value
            }
        }
private fun performPurchase(price: Double) {
    val totalPurse = playerGold + (playerSilver/100.0)
    println("You have $totalPurse gold")
    println("Purchase item for price $price")
    if(totalPurse >= price) {
        val remaining = totalPurse - price
        println("Remaining gold: ${"%.2f".format(remaining)}")
        playerGold = remaining.toInt();
        playerSilver = remaining.toString().split('.')[1].toInt()
        dragonsBreathQuantity -= 0.125
        println("${dragonsBreathQuantity / .125} quantity remaining")
        println(displayBalance())
    }else{
        println("Sorry, no money, dude")
    }
}
private fun displayBalance() =
        "You have a total of $playerGold and $playerSilver silver"

private fun printMenu(){
    println("*** Welcome to Taernyl's Folly ***")
    println()
    menuItems.forEach {
        val pointsToAdd = (maxLength - it.split(',')[1].length - it.split(',')[2].length)
        var points: String = ""
        (1..pointsToAdd).forEach{ _ ->
            points += "."
        }
        val type = "~[${it.split(",")[0]}]~"
        val meal = "${it.split(',')[1]}$points${it.split(',')[2]}"
        val numbOfWhiteSpaces = meal.length/2 - (type.length/2)
        var whiteSpaces = ""
        (1..numbOfWhiteSpaces).forEach {
            whiteSpaces += " "
        }
        println("$whiteSpaces$type")
        println("$meal")

    }
}

