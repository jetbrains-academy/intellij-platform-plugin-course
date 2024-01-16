class ComplexMethods {

    fun calculate(value: Int): Int {
        fun innerFunction(x: Int): Int {
            return x * x
        }
        return innerFunction(value)
    }

    companion object {
        fun staticMethod() {
            println("Static method in companion object")
        }
    }
}

fun anotherTopLevelFunction() {
    println("Another top-level function")
}
