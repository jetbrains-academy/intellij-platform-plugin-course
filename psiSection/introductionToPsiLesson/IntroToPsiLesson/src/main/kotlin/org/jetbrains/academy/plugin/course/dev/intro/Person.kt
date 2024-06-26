package org.jetbrains.academy.plugin.course.dev.intro

class Person(private val name: String, private val age: Int) {

    fun introduceTo(friend: Person) {
        println("Hi, ${friend.name}! My name is $name and I'm $age years old.")
    }
}
