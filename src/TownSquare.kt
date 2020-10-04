open class TownSquare: Room("Town Square"){
    private val bellSound = "Ting-Tong"
    override val dangerLevel: Int
        get() = super.dangerLevel - 3
     override fun load() = "The villagers rally and cheer as you enter!\n ${ringBell()}"
    private fun ringBell() = "Now i enter the TownSquare on the horse..the bell is $bellSound"
}