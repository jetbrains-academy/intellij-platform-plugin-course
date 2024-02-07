fun testFunction1(param1: String, param2: Int) {
    println(param1)
    println(param2)
}


fun testFunction2(param1: String, param2: Int) {
    println("$param1 = $param2")
}

fun test(age: Int) {
    println(age)
}

fun main() {
    testFunction1("Hello", 42)
}
