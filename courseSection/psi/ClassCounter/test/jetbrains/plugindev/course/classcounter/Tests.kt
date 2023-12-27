package jetbrains.plugindev.course.classcounter

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class Test : BasePlatformTestCase() {

    fun testSolution() {
        val file = myFixture.configureByText("MyClass.kt", """
            class MyFirstClass {
            }

            class MySecondClass {
            }
        """.trimIndent())

        val psiClassesCount = countKtClasses(file)
        assertEquals(2, psiClassesCount)
    }
}
