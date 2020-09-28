fun main() {
    val name = "Edgard"
    val healthPoints = 89
    val isBlessed = true
    val isImmortal =false;
    val race = "gnome"
    auraColor(healthPoints, isBlessed, isImmortal)
    val karma = (Math.pow(Math.random(), (110 - healthPoints) / 100.0) * 20 ).toInt()
    val auraColor = when(karma){
        in 0..5 -> "RED"
        in 6..10 -> "ORANGE"
        in 11..15 -> "PURPLE"
        in 16..20 -> "GREEN"
        else -> "UNKNOWN"
    }

    val healthStatus = formatHealthStatus(healthPoints, isBlessed)

    printLogic(auraColor, isBlessed, name, healthStatus)
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
    castFireball()
}

private fun auraColor(healthPoints: Int, isBlessed: Boolean, isImmortal: Boolean) {
    val healthSummary = if (healthPoints != 100) "Need healing!" else "Looking good."
    val auraVisible = isBlessed && healthPoints > 50 || isImmortal
}

private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean): String =
             when (healthPoints) {
        100 -> "is in excellent condition!"
        in 90..99 -> "has a few scratches."
        in 75..89 -> if (isBlessed) {
            "has some minor wounds but is healing quite quickly!"
        } else {
            "has some minor wounds."
        }
        in 15..74 -> "looks pretty hurt."
        else -> "is in awful condition!"
}

private fun castFireball(numFireball: Int = 1): String {
    "$numFireball fireballs throw to your face"
   return when(numFireball){
           in 1..10 -> "Tipsy"
            in 11..20 ->"Sloshed"
            in 21..30 -> "Soused"
            in 31..40 -> "Stewed"
            in 41..50 -> "t0a5t3d"
       else -> "unknown"
   }
}
