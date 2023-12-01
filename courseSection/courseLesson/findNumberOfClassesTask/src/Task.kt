import com.intellij.psi.PsiClass
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil

fun countPsiClasses(psiFile: PsiFile): Int {
    return PsiTreeUtil.findChildrenOfType(psiFile, PsiClass::class.java).toList().size
}
