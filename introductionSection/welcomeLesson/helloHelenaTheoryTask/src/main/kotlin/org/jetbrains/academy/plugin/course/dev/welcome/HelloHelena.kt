package org.jetbrains.academy.plugin.course.dev.welcome

fun sayHelloHelena(howManyTimes: Int): String = TODO("Not implemented yet")

fun main() {
    val howManyTimes = readlnOrNull()?.toIntOrNull() ?: 0
    println(sayHelloHelena(howManyTimes))
}
