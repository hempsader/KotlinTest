fun main() {
    val player = Player()

    printPlayerStatus(player)

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
private fun printPlayerStatus(player: Player){
    println("Aura: ${player.auraColor()}")
    println("Blessed: ${if (player.isBlessed) "YES" else "NO"}")
    println("${player.name} ${player.formatHealthStatus()}")
}






