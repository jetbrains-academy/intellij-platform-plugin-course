Annotations in Kotlin provide metadata about the code. 
They are a form of syntactic metadata that can be added to the code but do not directly affect the program's operation.

The `@Deprecated` annotation marks a program element as deprecated, indicating that the element should no longer be used.

**Task**:
Implement the function 
```kotlin
fun addDeprecatedAnnotations(psiFile: PsiFile)
```

This function should iterate over all Kotlin functions (`KtFunction`) in the given PSI file. 
For each function that lacks a `@Deprecated` annotation, one should be added, accompanied by a message that signifies the method's deprecation.

<div class="hint" title="How to find all function declarations">

Use `PsiTreeUtil.findChildrenOfType` to find all Kotlin function declarations (`KtFunction`) in the given PSI file.
</div>

<div class="hint" title="Creating annotations">

Use `KtPsiFactory` to create a new annotation entry. Learn how to instantiate `KtPsiFactory` and use it to create annotations.
</div>

<div class="hint" title="Checking for existing annotations">

Implement a helper method to check if a `KtFunction` already has a `@Deprecated` annotation. 
The `annotationEntries` property of 'KtFunction' might be useful for this.
</div>
