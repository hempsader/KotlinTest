fun main() {
    val value = readLine()?.let {
        if (it.isNotBlank()){
            it.capitalize()
        }else{
            "Not capitalise"
        }
    }
    println(value)
}