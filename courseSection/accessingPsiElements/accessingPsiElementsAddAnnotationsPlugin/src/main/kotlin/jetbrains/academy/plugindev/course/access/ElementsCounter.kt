package jetbrains.academy.plugindev.course.access.counter

import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtClass

fun countKtClasses(psiFile: PsiFile) =
    PsiTreeUtil.findChildrenOfType(psiFile, KtClass::class.java).toList().size
