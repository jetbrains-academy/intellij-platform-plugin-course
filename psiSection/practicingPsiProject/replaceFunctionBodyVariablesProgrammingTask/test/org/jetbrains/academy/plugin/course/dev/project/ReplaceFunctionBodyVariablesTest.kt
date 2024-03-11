package org.jetbrains.academy.plugin.course.dev.project

import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.kotlin.psi.KtNamedFunction


class ReplaceFunctionBodyVariablesTest : BasePlatformTestCase() {

    fun testSolution() {
        // TODO: add more test cases
        val fileContent = """
            fun testFunction1(param1: String, param2: Int) {
                println(param1)
                println(param2)
        }    
        """.trimIndent()
        val file = myFixture.configureByText("MyFile.kt", fileContent)
        val authorFile =  myFixture.configureByText("MyAuthorFile.kt", fileContent)

        val ktFunction = PsiTreeUtil.findChildrenOfType(file, KtNamedFunction::class.java).first()
        val authorFunction = PsiTreeUtil.findChildrenOfType(authorFile, KtNamedFunction::class.java).first()
        refactorFunctionBody(ktFunction)
        authorRefactorFunctionBody(authorFunction)
        assertEquals(ktFunction.bodyExpression?.text, authorFunction.bodyExpression?.text)
    }
}
