package org.jetbrains.academy.plugin.course.dev.project.examples

class NestedSortExample {

    fun calculate(value: Int): Int {

        fun innerFunction(x: Int): Int {
            return x * x
        }

        fun fistInnerFunction(x: Int): Int {
            return x + x
        }

        return innerFunction(value)
    }

    fun bigCalculate(value: Int): Int {
        return calculate(value)
    }

    companion object {
        fun staticMethod() {
            println("Static method in companion object")
        }

        fun anotherStaticMethod() {
            println("Another static method in companion object")
        }
    }
}