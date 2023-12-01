import org.junit.Assert
import org.junit.Test
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.jupiter.api.Assertions.assertEquals

class Test {
    @Test fun testSolution() {
        val file = myFixture.configureByText("MyClass.java", """
            public class MyClass1 {
                public void method1() {}
            }
            
            class MyClass2 {
                public void method2() {}
            }
        """.trimIndent())

        val stats = FileAnalyzer(myFixture.project).classCounter(file.virtualFile)
        assertEquals(2, stats)
    }
}