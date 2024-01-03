import com.intellij.testFramework.fixtures.BasePlatformTestCase
import jetbrains.academy.plugindev.course.access.countKtClasses

class Test : BasePlatformTestCase() {

    fun testSolution() {
        // TODO: add more test cases
        val fileContent = """
            class MyFirstClass {
            }

            class MySecondClass {
            }
        """.trimIndent()
        val file = myFixture.configureByText("MyClass.kt", fileContent)

        val psiClassesCount = countKtClasses(file)
        assertEquals("For the Kotlin file with content $fileContent the function countKtClasses should return 2, but currently it returns $psiClassesCount", 2, psiClassesCount)
    }
}
