package org.jetbrains.academy.plugin.course.dev.project

import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.academy.test.MyBaseTest
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction


abstract class BaseInsertDataClassTest : MyBaseTest() {

    fun doTest(relativePath: String) {
        val fileContent = getResourceFileContent(relativePath)
        val file = getFile(fileContent)
        val functions = PsiTreeUtil.findChildrenOfType(file, KtNamedFunction::class.java)

        functions.forEachIndexed { index, ktFunction ->
            val arguments = extractFunctionArguments(ktFunction)
            val dataClass = createDataClass("DataClass${index}", arguments)
            insertDataClass(dataClass, file)

            val classes = PsiTreeUtil.findChildrenOfType(file, KtClass::class.java)
            val insertedDataClass = classes.find { it.name == "DataClass${index}" }

            if (insertedDataClass == null) fail(("Data class was not found in the file."))

            insertedDataClass?.primaryConstructor?.valueParameters?.forEachIndexed { i, valueParameter ->
                assertEquals("Parameter name does not match the expected result.", arguments[i].name, valueParameter.name)
                assertEquals("Parameter type does not match the expected result.", arguments[i].typeReference?.text, valueParameter.typeReference?.text)
            }
        }
    }
}

class InsertDataClassTest : BaseInsertDataClassTest() {
    override val resourceClass: Class<*>
        get() = InsertDataClassTest::class.java

    fun testSolution() {
        doTest("Main.kt")
    }
}
