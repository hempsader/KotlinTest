interface Fightable {
    var healthPoints: Int
    val diceCount: Int
    val diceSide: Int
    val damageRoll: Int
            get() = (1..diceCount).map {
                java.util.Random().nextInt(diceSide + 1)
            }.sum()
    fun attack(opponent: Fightable): Int
}

abstract class Monster(val name: String, val description: String,override var healthPoints: Int): Fightable{
    override fun attack(opponent: Fightable): Int {
        val damageDealt = damageRoll
        opponent.healthPoints -= damageDealt
        return damageDealt
    }
}

class Goblin(): Monster("Goblin","A green litle thing",43) {

    override val diceCount: Int
        get() = 2
    override val diceSide: Int
        get() = 8
}