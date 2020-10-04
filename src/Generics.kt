class LootBox<T : Loot>(vararg item: T){
    var open = false
    private var loot: Array<out T> = item
    operator fun get(pos: Int): T? = loot[pos].takeIf { open }
    fun fetch(item: Int): T? {
        return loot[item].takeIf { open }
    }
    fun <R> fetch(item: Int,loodModFunction: (T) -> R): R?{
        return loodModFunction(loot[item]).takeIf { open }
    }

}

open class Loot(val value:Int)

class Fedora(val name: String,  value: Int): Loot(value)

class Coin(value: Int): Loot(value)

fun main(){
    val lootBoxOne = LootBox(Fedora("Fedora", 15), Fedora("Just another Fedora",30))
    val lootBoxTwo = LootBox(Coin(1))
    lootBoxOne.open = true
    lootBoxOne.fetch(1)?.run {
        println("You retrieve $name from to purse")
    }
    val coin = lootBoxOne.fetch(1) {
        Coin(it.value * 3)
    }
 //   coin?.let {
 //       println(it.value)
 //   }
    lootBoxOne.get(1)?.let {
        println("I got item ${it.name}")
    }



}