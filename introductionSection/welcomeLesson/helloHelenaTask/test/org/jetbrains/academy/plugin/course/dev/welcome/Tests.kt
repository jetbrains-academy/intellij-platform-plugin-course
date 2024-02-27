package org.jetbrains.academy.plugin.course.dev.welcome


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class Test(private val howManyTimes: Int) {

    private fun expectedSayHelloHelena(howManyTimes: Int) =
        List(howManyTimes) { HELLO_HELENA }.joinToString(System.lineSeparator())

    @Test
    fun test() {
        assertEquals(expectedSayHelloHelena(howManyTimes), sayHelloHelena(howManyTimes))
    }

    companion object {
        private const val HELLO_HELENA = "Hello, Helena!"
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(1, 5, 10, 100)
    }
}