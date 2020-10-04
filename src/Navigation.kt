data class Navigation(val x: Int, val y: Int){
    val inBounds = x >= 0 && y >= 0
    operator fun plus(other: Navigation) = Navigation(x + other.x, y + other.y)
}

enum class Direction(private val playerCoord: Navigation) {
    NORTH(Navigation(0,-1)),
    WEST(Navigation(-1,0)),
    SOUTH(Navigation(0,1)),
    EAST(Navigation(1,0));

    fun updateCoord(coord: Navigation) = playerCoord + coord
}