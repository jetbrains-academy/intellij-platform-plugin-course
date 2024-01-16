class MultipleMethods {
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun subtract(a: Int, b: Int): Int {
        return a - b
    }

    private fun privateMethod() {
        println("This is a private method")
    }
}

fun topLevelFunctionOne() {
    println("This is a top-level function")
}

fun topLevelFunctionTwo() {
    println("This is another top-level function")
}
