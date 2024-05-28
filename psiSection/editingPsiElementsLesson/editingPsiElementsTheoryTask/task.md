All operations involving code modifications, such as adding, deleting, or moving methods, renaming variables, etc., are implemented as PSI edits.
Please review the [Modify the PSI](https://plugins.jetbrains.com/docs/intellij/modifying-psi.html) documentation.

Common methods to edit PSI elements include:
* [`PsiElement.copy()`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L293) - Copies the PSI element subtree.
* [`PsiElement.replace()`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L402) - Replaces the PSI element in the tree.
* [`PsiElement.delete()`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L373) - Deletes the PSI element from the tree.
* [`PsiElement:add`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L302) - Adds a child to the PSI element subtree.
* [`PsiElement:addBefore`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L312C14-L312C23) / [`PsiElement:addAfter`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L322)  - Adds a child to the PSI element subtree before/after the specified anchor element.
* [`PsiElement:addRange`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L343) - Adds a list of children to the PSI element subtree.
* [`PsiElement:addRangeBefore`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L354) / [`PsiElement:addRangeAfter`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L365)- Adds a list of children to the PSI element subtree before/after the specified anchor element.


Moreover, there are some PsiElement-specific methods like [`KtNamedFunction:setName`](https://github.com/JetBrains/intellij-community/blob/bf3083ca66771e038eb1c64128b4e508f52acfad/platform/core-api/src/com/intellij/psi/PsiNamedElement.java#L39) and methods available to all `PsiNamedElement` inheritors.

**IMPORTANT!**

Every PSI modification needs to be wrapped in a [write action and command](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/command/WriteCommandAction.java)

```kotlin
WriteCommandAction.runWriteCommandAction(project) {
    // Here you can modify PSI elements
}
```
