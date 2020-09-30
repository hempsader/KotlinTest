fun main() {
    var beverege = readLine()
    beverege?.let {
        beverege = it.capitalize()
    } ?: println("simple ale")

    print(beverege)

}