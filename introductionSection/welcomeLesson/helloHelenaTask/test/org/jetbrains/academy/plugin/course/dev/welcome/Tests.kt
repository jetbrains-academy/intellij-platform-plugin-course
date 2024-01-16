package org.jetbrains.academy.plugin.course.dev.welcome


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class Test(private val howManyTimes: Int) {

    private fun expectedSayHelloHelena(howManyTimes: Int): String {
        return List(howManyTimes) { "Hello, Helena!" }.joinToString(System.lineSeparator())
    }

    @Test
    fun test() {
        assertEquals(expectedSayHelloHelena(howManyTimes), sayHelloHelena(howManyTimes))
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Int> {
            return listOf(1, 5, 10, 100)
        }
    }
}