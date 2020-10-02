import java.io.File

class Player(_name: String, var  healthPoints: Int = 100, _isBlessed: Boolean, private val _isImmortal: Boolean) {
    private val homeTown: String  by lazy {  homeTown() }
    val isBlessed = _isBlessed
    private val isImmortal = _isImmortal
    var name = _name
        get() = "${field.capitalize()} of " + homeTown
        private set(value) {
            field = value.trim()
        }
    init {
        require(healthPoints > 0){"Healthpoints need to be greater than 0"}
        require(name.isNotBlank()) {"Name is empty"}
    }
    constructor(name: String):this(name, _isBlessed = true, _isImmortal = false  ){
        if(name.toLowerCase()== "pula") healthPoints = 40
    }


    fun castFireball(numbFireballs: Int = 2) = println("A surge of $numbFireballs fireballs")
    private fun homeTown() =
        File("src/data/towns.txt").readText().split('\n').shuffled().first().toString()

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