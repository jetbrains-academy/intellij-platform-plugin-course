package org.jetbrains.academy.plugin.course.dev.project

import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.academy.test.MyBaseTest
import org.jetbrains.kotlin.psi.KtNamedFunction

abstract class BaseCreateDataClassTest : MyBaseTest() {

    fun doTest(relativePath: String) {
        val fileContent = getResourceFileContent(relativePath)
        val file = getFile(fileContent)
        val functions = PsiTreeUtil.findChildrenOfType(file, KtNamedFunction::class.java)

        for (ktFunction in functions) {
            val arguments = extractFunctionArguments(ktFunction)
            val dataClass = createDataClass("DataClass", arguments)
            val expectedDataClass = authorCreateDataClass("DataClass", arguments)
            assertEquals("Generated data class does not match the expected result.", expectedDataClass.trim(), dataClass.trim())
        }
    }
}

class CreateDataClassTest : BaseCreateDataClassTest() {
    override val resourceClass: Class<*>
        get() = CreateDataClassTest::class.java

    fun testSolution() {
        doTest("Main.kt")
    }
}
