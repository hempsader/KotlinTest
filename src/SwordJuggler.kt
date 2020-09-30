import java.io.File
import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"
var playerGold = 10
var playerSilver = 9
var dragonsBreathQuantity: Double = 5.0
val lastName = mutableListOf("Eli", "Mordoc", "Sophie")
val firstNames = listOf("Sam","Frodo","Gandalf")
var patrons = setOf<String>()
val menuItems = File("src/data/menu_items.txt").readText().split("\n")

fun main() {
    (1..20).forEach {
        val name = "${firstNames.shuffled().first()} ${lastName.shuffled().first()}"
        patrons += name
    }
    val max = menuItems.maxOf {
        it.length
    }
    println(max)
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


