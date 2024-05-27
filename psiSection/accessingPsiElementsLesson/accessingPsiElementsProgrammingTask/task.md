Using your newly gained knowledge about the `PsiTreeUtil.findChildrenOfType` method, implement the functions `countKtClasses` and `countKtFunctions`. These functions will
count the number of Kotlin classes and functions, respectively, declared in the given Kotlin PSI file.

<div class="hint" title="Which class should I use as the aClass parameter?">

Try using `KtClass::class.java` for classes and `KtNamedFunction::class.java` for functions as the aClass parameter values for `findChildrenOfType`.
</div>

**Run Plugin**

To see your implemented methods in action within the UI, run the following command in your project's root directory:

* ` ./gradlew :psiSection-accessingPsiElementsLesson-task:runIde
  ` on Linux or MacOS
* ` gradlew :psiSection-accessingPsiElementsLesson-task:runIde
  ` on Windows




