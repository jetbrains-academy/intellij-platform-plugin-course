package org.jetbrains.academy.plugin.course.dev.welcome

fun sayHelloHelena(howManyTimes: Int) =
    List(howManyTimes) { "Hello, Helena!" }.joinToString(System.lineSeparator())

fun main() {
    val howManyTimes = readlnOrNull()?.toIntOrNull() ?: 0
    println(sayHelloHelena(howManyTimes))
}
