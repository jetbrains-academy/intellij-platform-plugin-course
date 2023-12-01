
# Intro to Psi

---
PSI stands for Program Structure Interface. It's a part of IntelliJ Platform SDK.
It represents the entire structure of the code in a project as a tree of elements, called a PSI tree.

**PSI Tree Structure**
* The root is a **PsiFile** element, representing an entire file.
* Each node/element in the tree represents a syntactic or structural part of the code, like expressions, statements, and declarations.

**Types of PSI Elements**
* PsiFile: Represents an entire file.
* PsiClass: Represents a class in the code.
* PsiMethod: Represents a method.
* PsiVariable: Represents a variable.
* PsiExpression: Represents an expression, and so on.

**Accessing PSI Elements**

**PsiTreeUtil** is a utility class in the IntelliJ Platform SDK that provides methods for navigating and querying the PSI tree of a project. 

It's a part of the `com.intellij.psi.util package`.

The **findChildrenOfType** method is used to find all children of a specified type within a given PSI element. 
It's particularly useful when you need to locate all instances of a particular element type, such as classes, methods, or variables, within a file or a code block.

Syntax:
`public static @Unmodifiable @NotNull <T extends PsiElement> Collection<T> findChildrenOfType(@Nullable PsiElement element, @NotNull Class<? extends T> aClass)`

**Parameters**
* element: The PSI element within which to search for children. This could be a PsiFile, a PsiClass, or any other PSI element.
* aClass: The class type of the elements you are searching for. For example, PsiClass.class to find all classes.