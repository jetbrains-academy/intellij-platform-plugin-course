package org.jetbrains.academy.plugin.course.dev.edit

import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction

abstract class BaseSortMethodsTest : BasePlatformTestCase() {
    private fun getResourceFileContent(relativePath: String) =
        SortMethodsTest::class.java.getResourceAsStream(BASE_PATH + relativePath)?.bufferedReader()?.use { it.readText() } ?: ""
    private fun getFile(fileContent: String) =
        myFixture.configureByText("MyClass.kt", fileContent) ?: error("Internal course error!")

    fun doTest(relativePath: String) {
        val fileContent = getResourceFileContent(relativePath)
        val file = getFile(fileContent)
        val ktClasses = PsiTreeUtil.findChildrenOfType(file, KtClass::class.java)

        for (ktClass in ktClasses) {
            sortMethods(ktClass)
            val methods = ktClass.declarations.filterIsInstance<KtNamedFunction>()
            val sortedMethods = methods.sortedBy { it.name }

            assertEquals(
                "For the Kotlin file with content $fileContent the function sortMethods should sort methods alphabetically, for class ${ktClass.name} expected output is ${sortedMethods}, but actual is $methods",
                sortedMethods,
                methods
            )
        }
    }

    companion object {
        private const val BASE_PATH = "/backUpProjects/sort-methods-project/src/main/kotlin/org/jetbrains/academy/plugin/course/dev/project/examples/"
    }
}
class SortMethodsTest : BaseSortMethodsTest() {

    fun testSolution() {
        doTest("AlphabeticalSortExample.kt")
        doTest("NestedSortExample.kt")
    }
}
