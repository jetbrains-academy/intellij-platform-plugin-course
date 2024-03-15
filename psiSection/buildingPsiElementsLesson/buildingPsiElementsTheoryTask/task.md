During this lesson we are going to cover details around addition of PSI elements in the PSI tree.

There are three ways to do it:
* [`PsiElement:add`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L302) - to add a child to PSI Element into the tree
* [`PsiElement:addBefore`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L312C14-L312C23) - to add a child to PSI Element into the tree before specific child
* [`PsiElement:addAfter`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L322) - to add a child to PSI Element into the tree after specific child
* [`PsiElement:addRange`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L343) - to add a child to PSI Element into the tree after specific child
* [`PsiElement:addRangeBefore`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L354) - to add a child to PSI Element into the tree after specific child
* [`PsiElement:addRangeAfter`](https://github.com/JetBrains/intellij-community/blob/19d9a1cc2d9c14df9c3bdee391e9e4795ac25cb9/platform/core-api/src/com/intellij/psi/PsiElement.java#L365) - to add a child to PSI Element into the tree after specific child

Moreover, you can create PSI element not just by coping them, but also from string! 
Let me introduce you to [`PsiElementFactory`](https://github.com/JetBrains/intellij-community/blob/9717f44ad7e6dfb9cd502f6e690e87c4e27d36d0/java/java-psi-api/src/com/intellij/psi/PsiElementFactory.java#L24) using which you can:
* [`PsiElementFactory:createClass`](https://github.com/JetBrains/intellij-community/blob/9717f44ad7e6dfb9cd502f6e690e87c4e27d36d0/java/java-psi-api/src/com/intellij/psi/PsiElementFactory.java#L50)
* [`PsiElementFactory:createMethod`](https://github.com/JetBrains/intellij-community/blob/9717f44ad7e6dfb9cd502f6e690e87c4e27d36d0/java/java-psi-api/src/com/intellij/psi/PsiElementFactory.java#L105)
* And many other PSI Elements

Also for Kotlin specific PSI Elements you can use 
**ONE MORE TIME!**

Every PSI modifications need to be wrapped in a [write action and in command](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/command/WriteCommandAction.java)

```kotlin
WriteCommandAction.runWriteCommandAction(project) {
    // Here you can modify PSI Elements
}
```
