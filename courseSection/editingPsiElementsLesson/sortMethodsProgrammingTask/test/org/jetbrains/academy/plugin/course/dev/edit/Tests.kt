package org.jetbrains.academy.plugin.course.dev.edit

import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction

class Test : BasePlatformTestCase() {

    fun testSolution() {
        // TODO: add more test cases
        val fileContent = """
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
            
        """.trimIndent()
        val file = myFixture.configureByText("Person.kt", fileContent)
        val classes = PsiTreeUtil.findChildrenOfType(file, KtClass::class.java)
        for (ktClass in classes) {
            sortMethods(ktClass)
            val methods = ktClass.declarations.filterIsInstance<KtNamedFunction>()
            val sorted = methods.sortedBy { it.name }
            assertEquals(
                "For the Kotlin file with content $fileContent the function sortMethods should sort methods alphabetically, for class ${ktClass.name} expected output is ${sorted}, but actual is $methods",
                sorted,
                methods
            )
        }
    }
}