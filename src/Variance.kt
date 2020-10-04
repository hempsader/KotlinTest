class Barrel<out T>(val item: T)

fun main(){
    var fedoraBarrel = Barrel(Fedora("generic Fedora",15))
    var lootBarrel = Barrel<Loot>(Coin(15))
    lootBarrel = fedoraBarrel
    val myFedora = fedoraBarrel.item
}