import com.intellij.ide.highlighter.JavaFileType
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiMethod
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import junit.framework.TestCase
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

    fun testAnnotation() {
        val javaFile = myFixture.configureByText(
            JavaFileType.INSTANCE, """
        class SuperClass {
            public void testMethod() {}
        }

        class SubClass extends SuperClass {
            
            public void testMethod() {}
        }
    """.trimIndent())

        addAnnotations(javaFile)
        val subClass = PsiTreeUtil.findChildrenOfType(javaFile, PsiClass::class.java)
            .first { it.name == "SubClass" }
        val methods = PsiTreeUtil.findChildrenOfType(subClass, PsiMethod::class.java)

        val testMethod = methods.first { it.name == "testMethod" }
        val hasOverrideAnnotation = testMethod.modifierList.findAnnotation("Override") != null

        // Assert that the @Override annotation is present
        assertTrue("The method 'testMethod' should have @Override annotation", hasOverrideAnnotation)

    }
}