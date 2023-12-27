package jetbrains.plugindev.course.classcounter

import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtClass

fun countKtClasses(psiFile: PsiFile): Int {
    return PsiTreeUtil.findChildrenOfType(psiFile, KtClass::class.java).toList().size
}
