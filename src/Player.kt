class Player {
    val healthPoints = 89
    var name = "madrigal"
        get() = field.capitalize()
        private set(value) {
            field = value.trim()
        }
    val isBlessed = true
    private val isImmortal = false

    fun castFireball(numbFireballs: Int = 2) = println("A surge of $numbFireballs fireballs")

    fun auraColor() {
        val healthSummary = if (healthPoints != 100) "Need healing!" else "Looking good."
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
    }

    fun formatHealthStatus(): String =
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


}