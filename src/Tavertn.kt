import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"
val lastName = mutableListOf("Eli", "Mordoc", "Sophie")
val firstNames = listOf("Sam","Frodo","Gandalf")
var patrons = mutableSetOf<String>()
val menuItems = File("src/data/menu_items.txt").readText().split("\n")
val maxLength = menuItems.maxOf {
    val drinkName = it.split(',')
    return@maxOf (drinkName[1]+drinkName[2]).length+14
}
val patronGold = mutableMapOf<String,Double>()


fun main() {

    printMenu()
    (1..20).forEach { _ ->
        patrons.add("${lastName.shuffled().last()} ${firstNames.shuffled().last()}")
    }

    patrons.forEach {
        patronGold.putIfAbsent(it,6.0)
    }
    patrons.forEach { println(it) }

    placeOrder("Eli Gandalf",menuItems[1])
}

fun placeOrder(patronName: String,menuData: String) {

    val indexAphostrope = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexAphostrope)
    println("$patronName it's speaking with tavern master $tavernMaster to place an order")

    val data = menuData.split(',')
    val (name, type, price) = data
    performPurchase(patronName,price.toDouble())
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
private fun performPurchase(patron: String,price: Double) {
    println("You have ${if (patrons.contains(patron)) "${patronGold.getValue(patron)} gold" else "Patron don't exists"}")

    if(patrons.contains(patron) && patronGold.getValue(patron) >= price) {
        val patronInitialGold = patronGold.getValue(patron)
        patronGold[patron] = (patronInitialGold - price)
        println("Remaining gold: ${"%.2f".format(patronGold.getValue(patron))}")
        println(displayBalance(patron))
    }else{
        println("Sorry, no money, dude")
    }
}
private fun displayBalance(patron: String) =
        if(patrons.contains(patron)) "You have a total of ${patronGold.getValue(patron)} gold}" else "Patron not found"

private fun printMenu(){
    println("*** Welcome to Taernyl's Folly ***")
    println()
    menuItems.forEach {
        val pointsToAdd = (maxLength - it.split(',')[1].length - it.split(',')[2].length)
        var points = ""
        (1..pointsToAdd).forEach{ _ ->
            points += "."
        }
        val type = "~[${it.split(",")[0]}]~"
        val meal = "${it.split(',')[1]}$points${it.split(',')[2]}"
        val numbOfWhiteSpaces = meal.length/2 - (type.length/2)
        var whiteSpaces = ""
        (1..numbOfWhiteSpaces).forEach { _ ->
            whiteSpaces += " "
        }
        println("$whiteSpaces$type")
        println(meal)

    }
}

