import com.intellij.testFramework.fixtures.BasePlatformTestCase

class Test : BasePlatformTestCase() {
    fun testSolution() {
        val file = myFixture.configureByText("MyClass.kt", """
            class MyFirstClass {
            }

            class MySecondClass {
            }
        """.trimIndent())

        val psiClassesCount = countPsiClasses(file)
        assertEquals(2, psiClassesCount)
    }
}
