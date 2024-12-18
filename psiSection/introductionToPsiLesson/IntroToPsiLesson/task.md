# Task 1/2: PSI: What is it?

PSI stands for Program Structure Interface. It's a part of the IntelliJ Platform SDK. Please have a brief look at the
[PSI Documentation](https://plugins.jetbrains.com/docs/intellij/psi.html) on the IntelliJ Platform SDK Documentation web page.
As you probably read there, PSI represents the entire structure of the code in a project as a tree of elements,
called a PSI tree.

### PSI Tree Structure
* The root is a [`PSI File`](https://plugins.jetbrains.com/docs/intellij/psi-files.html) element, representing an entire file.
* Each node, or [`PSI Element`](https://plugins.jetbrains.com/docs/intellij/psi-elements.html), in the tree represents a syntactic or structural part of the code, such as expressions,
  statements, and declarations.

### Types of PSI Elements
* PsiFile: Represents an entire file
* PsiClass: Represents a class in the code
* PsiMethod: Represents a method
* PsiVariable: Represents a variable
* PsiExpression: Represents an expression, and so on

Each language (Java, Kotlin, Python, etc.) has its own implementation of [`PsiElement`](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiElement.java).
For example, for file abstraction, there are the following options:
* [`com.intellij.psi.PsiFile`](https://github.com/JetBrains/intellij-community/blob/idea/232.10227.8/platform/core-api/src/com/intellij/psi/PsiFile.java): Represents a Java file
* [`org.jetbrains.kotlin.psi.KtFile`](https://github.com/JetBrains/kotlin/blob/master/compiler/psi/src/org/jetbrains/kotlin/psi/KtFile.kt): Represents a Kotlin file
* [`com.jetbrains.python.psi.PyFile`](https://github.com/JetBrains/intellij-community/blob/master/python/python-psi-api/src/com/jetbrains/python/psi/PyFile.java#L28): Represents a Python file

