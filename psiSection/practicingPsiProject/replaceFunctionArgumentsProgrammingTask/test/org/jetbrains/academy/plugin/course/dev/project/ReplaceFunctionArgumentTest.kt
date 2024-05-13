package org.jetbrains.academy.plugin.course.dev.project

import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.academy.test.MyBaseTest
import org.jetbrains.kotlin.psi.KtNamedFunction

abstract class BaseReplaceFunctionArgumentsTest : MyBaseTest() {

    fun doTest(relativePath: String) {
        val fileContent = getResourceFileContent(relativePath)
        val file = getFile(fileContent)
        val functions = PsiTreeUtil.findChildrenOfType(file, KtNamedFunction::class.java)

        functions.forEachIndexed {index, ktFunction ->
            val arguments = extractFunctionArguments(ktFunction)
            val name = "DataClass${index}"
            val dataClass = createDataClass(name, arguments)
            insertDataClass(dataClass, file)
            replaceFunctionArguments(ktFunction, name)
            // Assert that the function now has a single parameter of type TestDataClass
            val actualParamsText = ktFunction.valueParameterList?.text
            assertEquals("(data: ${name})", actualParamsText)
        }
    }
}

class ReplaceFunctionArgumentsTest : BaseReplaceFunctionArgumentsTest() {
    override val resourceClass: Class<*>
        get() = ReplaceFunctionArgumentsTest::class.java

    fun testSolution() {
        doTest("Main.kt")
    }
}
