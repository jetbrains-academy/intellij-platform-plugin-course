import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction

class Test : BasePlatformTestCase() {

    fun testSolution() {
        var file = myFixture.configureByText("MyClass.kt", """
            class MyClass {
                fun abcd() {
                    println("Hello")
                }
                
                fun zhas() {
                    println("Goodbye")
                }
                
                fun mid() {
                    println("Goodbye")
                }
            }
            
        """.trimIndent())

        sortMethods(file)
        val classes = PsiTreeUtil.findChildrenOfType(file, KtClass::class.java)

        for (ktClass in classes){
            val methods = ktClass.declarations.filterIsInstance<KtNamedFunction>()
            val sorted = methods.sortedBy { it.name }
            assertEquals(sorted, methods)
        }
    }
}