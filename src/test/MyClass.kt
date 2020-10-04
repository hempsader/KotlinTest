package test

class MyClass(val name: String){
    init {
        println("Hello $name")
    }

    companion object {
        fun load()
        {
            println("${MyClass.javaClass.name} is the name of this class")
        }

    }
}

fun main()
{
    MyClass.load()
}