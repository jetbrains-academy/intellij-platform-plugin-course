All operations with code like adding, deleting or moving methods, renaming variable, you name it, are implemented as PSI editions.
Please skim the [Modify the PSI](https://plugins.jetbrains.com/docs/intellij/modifying-psi.html) documentation.

Methods to edit PSI Element:
* [`PsiElement.add()`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L302) - to add a child to PSI Element into tree. To specify the place in children list use [`PsiElement.addBefore()`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L312C14-L312C23) and [`PsiElement.addAfter()`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L322)
* [`PsiElement.delete()`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L373) - to delete PSI Element from tree
* [`PsiElement.replace()`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L402) - to replace PSI Element in tree

Also, you create PSI Elements by using:
* [`PsiElement.copy()`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L293) - to copy PSI Element subtree

Moreover, there are some PisElement-specific methods like [`KtNamedFunction.setName()`](https://github.com/JetBrains/intellij-community/blob/bf3083ca66771e038eb1c64128b4e508f52acfad/platform/core-api/src/com/intellij/psi/PsiNamedElement.java#L39) as well as all `PsiNamedElement` inheritors.

**IMPORTANT!**

Every PSI modifications need to be wrapped in a [write action and in command](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/command/WriteCommandAction.java)

```kotlin
WriteCommandAction.runWriteCommandAction(project) {
    // Here you can modify PSI Elements
}
```
