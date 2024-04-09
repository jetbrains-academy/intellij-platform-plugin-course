package org.jetbrains.academy.plugin.course.dev.add

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class Test : BasePlatformTestCase() {

    fun testAnnotation() {
        // TODO: add more test cases
        val fileContent = """
            fun sayHello() {
                println("Hello")
            }
            
            fun sayGoodbye() {
                println("Goodbye")
            }
            
            @Deprecated
            fun sayChao() {
                println("Goodbye")
            }
        """.trimIndent()

        val requiredFileContent = """
            @Deprecated("This method is deprecated")
            fun sayHello() {
                println("Hello")
            }
            
            @Deprecated("This method is deprecated")
            fun sayGoodbye() {
                println("Goodbye")
            }
            
            @Deprecated
            fun sayChao() {
                println("Goodbye")
            }
        """.trimIndent()
        val file = myFixture.configureByText("MyClass.kt", fileContent)
        addDeprecatedAnnotations(file)
        assertEquals(file.text, requiredFileContent)
    }
}