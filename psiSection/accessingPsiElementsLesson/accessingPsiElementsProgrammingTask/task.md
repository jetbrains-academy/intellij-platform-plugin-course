Using your newly gained knowledge about `PsiTreeUtil.findChildrenOfType` method, implement functions `countKtClasses` and `countKtFunctions` which will
count number of kotlin classes of functions relatively declared in the given kotlin PSI file.

<div class="hint" title="Which class should I use as aClass parameter?">

Try to use `KtClass::class.java` for classes and `KtNamedFunction::class.java` for functions value for aClass parameter for `findChildrenOfType`
</div>

**Run Plugin**

To see your implemented methods in action within a UI, run the following command in your project's root directory:

* ` ./gradlew :psiSection-accessingPsiElementsLesson-task:runIde
  ` on Linux or MacOS
* ` gradlew :psiSection-accessingPsiElementsLesson-task:runIde
  ` on Windows




