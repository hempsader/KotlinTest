fun main() {

    runSimulation("Ionut",::cost) { playerName, numOfBuildings ->
        val year = 2020
        println("adding $numOfBuildings houses")
        "welcome back $playerName"
    }
    runSimulation()
}


inline fun  runSimulation(playerName:String, cost: (Int) -> Unit, greetingFunction: (String, Int) -> String)
{
    val numOfBuildings = (1..3).shuffled().last()
    cost(numOfBuildings)
    println(greetingFunction(playerName,numOfBuildings))
}

fun cost(numbOfBuildings: Int) {
    val cost = 500 * numbOfBuildings
    println("construction cost $cost")
}

fun configureGreetingsFunction (): (String) -> String {
    val structures = "barraacks"
    var buildings = 10
    return {
        val year = 2020
        buildings += 1
        println("Adding $buildings buildings of type $structures")
        "Welcome to simcity, $it"
    }
}

fun runSimulation() {
    val greetings = configureGreetingsFunction();
    println(greetings("Ionut"))
}