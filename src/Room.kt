open class Room(val name: String){
    protected  open val dangerLevel = 5
    val monster: Monster? = Goblin()
    fun description() = "Room $name\nDanger level: $dangerLevel" +
            "\nMonster: ${monster?.name ?: "Name"}"
    open fun load() = "Nothing to load here..."
}