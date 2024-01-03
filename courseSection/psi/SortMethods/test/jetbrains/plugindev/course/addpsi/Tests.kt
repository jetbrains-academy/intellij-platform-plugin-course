package jetbrains.plugindev.course.addpsi

import com.intellij.ide.highlighter.JavaFileType
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiMethod
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction

class Test : BasePlatformTestCase() {

    fun testSolution() {
        var file = myFixture.configureByText("Person.kt", """
            class Person {
                fun name() {
                    println("My name is John")
                }
                
                fun surname() {
                    println("My surname is Johnson")
                }
                
                fun age() {
                    println("I am 18 years old")
                }
            }
            
        """.trimIndent())

        val classes = PsiTreeUtil.findChildrenOfType(file, KtClass::class.java)
        for (ktClass in classes){
            sortMethods(ktClass)

            val methods = ktClass.declarations.filterIsInstance<KtNamedFunction>()
            val sorted = methods.sortedBy { it.name }
            assertEquals(sorted, methods)
        }
    }


}