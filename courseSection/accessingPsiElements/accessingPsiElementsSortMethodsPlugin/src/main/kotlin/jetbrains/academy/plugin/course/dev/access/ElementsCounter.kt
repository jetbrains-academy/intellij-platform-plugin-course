package jetbrains.academy.plugin.course.dev.access

import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtClass

fun countKtClasses(psiFile: PsiFile) =
    PsiTreeUtil.findChildrenOfType(psiFile, KtClass::class.java).toList().size
