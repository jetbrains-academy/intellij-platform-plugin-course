package org.jetbrains.academy.ij.plugin.course.annotations


import com.intellij.ide.highlighter.JavaFileType
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiMethod
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase


class Test : BasePlatformTestCase() {

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