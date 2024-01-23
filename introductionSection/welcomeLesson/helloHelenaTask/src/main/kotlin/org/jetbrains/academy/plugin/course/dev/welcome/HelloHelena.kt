package org.jetbrains.academy.plugin.course.dev.welcome

fun sayHelloHelena(howManyTimes: Int): String {
    return List(howManyTimes) { "Hello, Helena!" }.joinToString(System.lineSeparator())
}

fun main() {
    val howManyTimes = readln().toInt()
    print(sayHelloHelena(howManyTimes))
}
