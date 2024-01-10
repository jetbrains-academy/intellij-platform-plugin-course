[com.intellij.psi.util.PsiTreeUtil](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/util/PsiTreeUtil.java)
is a utility class in the IntelliJ Platform SDK that provides methods for navigating and querying the PSI tree of a project.

The [com.intellij.psi.util.PsiTreeUtil.findChildrenOfType](https://github.com/JetBrains/intellij-community/blob/30cfa651ac2b9c50163368b56ee87ce1944543ec/platform/core-api/src/com/intellij/psi/util/PsiTreeUtil.java#L197C64-L197C64) method is used to find all children of a specified type within a given PSI element.
It's particularly useful when you need to locate all instances of a particular element type, such as classes, methods, or variables, within a file or a code block.

**Syntax:**
```java
public static @Unmodifiable @NotNull <T extends PsiElement> Collection<T> findChildrenOfType(@Nullable PsiElement element, @NotNull Class<? extends T> aClass)
```

**Parameters:**
* **element**: The PSI element within which to search for children. This could be a PsiFile, a PsiClass, or any other PSI element.
* **aClass**: The class type of the elements you are searching for. For example, PsiClass.class to find all classes.