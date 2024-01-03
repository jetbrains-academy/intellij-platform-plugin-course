import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import jetbrains.academy.plugindev.course.access.editFunctionName
import org.jetbrains.kotlin.psi.KtNamedFunction
class Test : BasePlatformTestCase() {

    fun testSolution() {
        // TODO: add more test cases
        val fileContent = """
            fun sayHello() {
                println("Hello")
            }
            
            fun sayGoodbye() {
                println("Goodbye")
            }
        """.trimIndent()
        val file = myFixture.configureByText("MyClass.kt", fileContent)

        val functions = PsiTreeUtil.findChildrenOfType(file, KtNamedFunction::class.java)
        val functionToRename = functions.filter { it.name == "sayHello" }
        require(functionToRename.size == 1) { "Internal error! Could not find sayHello function!" }
        editFunctionName(functionToRename.first(), "greetings")

        assertTrue("For the Kotlin file with content $fileContent the function editFunctionName should rename sayHello function into greetings, but it does not!", functions.any { it.name == "greetings" })
    }
}