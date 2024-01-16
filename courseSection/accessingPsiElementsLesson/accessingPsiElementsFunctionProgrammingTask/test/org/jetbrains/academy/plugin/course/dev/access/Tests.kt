package org.jetbrains.academy.plugin.course.dev.access

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class Test : BasePlatformTestCase() {

    fun testSolution() {
        // TODO: add more test cases
        val fileContent = """
            class MyClass {
    fun instanceFunction1() {
        // Function implementation
    }

    fun instanceFunction2() {
        // Function implementation
    }

    companion object {
        fun staticFunction1() {
            // Function implementation
        }
    }
}

fun topLevelFunction1() {
    // Function implementation
}

fun topLevelFunction2() {
    // Function implementation
}

object MyObject {
    fun objectFunction() {
        // Function implementation
    }
}
        """.trimIndent()
        val file = myFixture.configureByText("MyClass.kt", fileContent)

        val psiFunctionsCount = countKtFunctions(file)
        val authorFunctionsCount = authorCountKtFunctions(file)
        assertEquals("For the Kotlin file with content $fileContent the function countKtClasses should return 6, but currently it returns $psiFunctionsCount", authorFunctionsCount, psiFunctionsCount)
    }
}
