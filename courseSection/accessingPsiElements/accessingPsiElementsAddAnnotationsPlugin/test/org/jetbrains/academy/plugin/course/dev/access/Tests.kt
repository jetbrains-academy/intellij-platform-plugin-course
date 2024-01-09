package org.jetbrains.academy.plugin.course.dev.access

import com.intellij.ide.highlighter.JavaFileType
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiMethod
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.academy.plugin.course.dev.access.addAnnotations

class Test : BasePlatformTestCase() {

    fun testAnnotation() {
        // TODO: add more test cases
        val fileContent = """
            class SuperClass {
                public void testMethod() {}
            }
    
            class SubClass extends SuperClass {
                public void testMethod() {}
            }
        """.trimIndent()
        val javaFile = myFixture.configureByText(JavaFileType.INSTANCE, fileContent)
        addAnnotations(javaFile)

        val subClass = PsiTreeUtil.findChildrenOfType(javaFile, PsiClass::class.java)
            .first { it.name == "SubClass" }
        val methods = PsiTreeUtil.findChildrenOfType(subClass, PsiMethod::class.java)

        val testMethod = methods.first { it.name == "testMethod" }
        val hasOverrideAnnotation = testMethod.modifierList.findAnnotation("Override") != null

        // Assert that the @Override annotation is present
        assertTrue("For the Kotlin file with content $fileContent the function addAnnotations should add the @Override annotation to the `testMethod`", hasOverrideAnnotation)
    }
}