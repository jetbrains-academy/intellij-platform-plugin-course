package jetbrains.plugindev.course.editpsi

import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.kotlin.psi.KtNamedFunction

class Test : BasePlatformTestCase() {

    fun testSolution() {
        var file = myFixture.configureByText("MyClass.kt", """
            fun sayHello() {
                println("Hello")
            }
            
            fun sayGoodbye() {
                println("Goodbye")
            }
        """.trimIndent())

        val functions = PsiTreeUtil.findChildrenOfType(file, KtNamedFunction::class.java)
        val functionToRename = functions.find { it.name == "sayHello" }
        if (functionToRename != null) {
            editFunctionName(functionToRename, "greetings")
        }

        val hasNewFunction = functions.any { it.name == "greetings" }
        assertTrue(hasNewFunction)
    }
}