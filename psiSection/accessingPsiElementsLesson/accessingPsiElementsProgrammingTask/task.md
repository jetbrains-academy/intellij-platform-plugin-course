Using your newly gained knowledge about `PsiTreeUtil.findChildrenOfType` method, implement functions `countKtClasses` and `countKtFunctions` which will
count number of kotlin classes of functions relatively declared in the given kotlin PSI file.

<div class="hint" title="Which class should I use as aClass parameter?">

Try to use `KtClass::class.java` for classes and `KtNamedFunction::class.java` for functions value for aClass parameter for `findChildrenOfType`
</div>

