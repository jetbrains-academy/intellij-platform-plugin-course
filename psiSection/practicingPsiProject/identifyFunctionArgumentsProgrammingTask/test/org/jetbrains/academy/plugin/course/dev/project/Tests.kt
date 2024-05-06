package org.jetbrains.academy.plugin.course.dev.project

import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.academy.test.MyBaseTest
import org.jetbrains.kotlin.psi.KtNamedFunction


abstract class BaseIdentifyArgumentsTest : MyBaseTest() {

    fun doTest(relativePath: String) {
        val fileContent = getResourceFileContent(relativePath)
        val file = getFile(fileContent)
        val functions = PsiTreeUtil.findChildrenOfType(file, KtNamedFunction::class.java)

        for (ktFunction in functions) {
            val arguments = extractFunctionArguments(ktFunction)
            assertEquals(
                "For the Kotlin file with content $fileContent the function extractFunctionArguments should return function arguments, for function ${ktFunction.name} expected output is ${ktFunction.valueParameters}, but actual is $arguments",
                ktFunction.valueParameters,
                arguments
            )
        }

    }
}

class IdentifyArgumentsTest : BaseIdentifyArgumentsTest() {
    override val resourceClass: Class<*>
        get() = IdentifyArgumentsTest::class.java

    fun testSolution() {
        doTest("Main.kt")
    }
}
