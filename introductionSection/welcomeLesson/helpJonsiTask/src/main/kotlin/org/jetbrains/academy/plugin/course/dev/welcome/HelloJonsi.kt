package org.jetbrains.academy.plugin.course.dev.welcome

fun sayHelloJonsi(howManyTimes: Int) =
    List(howManyTimes) { "Hello, Jonsi!" }.joinToString(System.lineSeparator())

fun main() {
    val howManyTimes = readlnOrNull()?.toIntOrNull() ?: 0
    println(sayHelloJonsi(howManyTimes))
}
