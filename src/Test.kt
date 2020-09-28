import java.util.function.Consumer
import java.util.function.Predicate

fun main() {
    val country = "Romania".count { letter -> letter == 'm' }
    val greetings = { name: String, buildings:Int  ->
        val number = 30
        "welcome $name you have $buildings"
    }
    print(runSimulation("Ionut",{name, buildings ->
        val year = 2020
        "$name welcome back you have $buildings"
    }))
}

private fun greetingsFun(): String {
    val name = "Ionut"
    return "welcome back $name"
}

private inline fun runSimulation(playerName: String, greetingFunction: (String, Int) -> String){
    val buildings = (1..3).shuffled().last()
    println(greetingFunction(playerName,buildings))
}