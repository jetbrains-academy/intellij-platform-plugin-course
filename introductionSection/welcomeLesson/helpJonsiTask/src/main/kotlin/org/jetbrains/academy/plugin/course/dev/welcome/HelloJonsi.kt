package org.jetbrains.academy.plugin.course.dev.welcome

fun sayHelloJonsi(howManyTimes: Int): String {
    return List(howManyTimes) { "Hello, Jonsi!" }.joinToString(System.lineSeparator())
}

fun main() {
    val howManyTimes = readln().toInt()
    print(sayHelloJonsi(howManyTimes))
}
