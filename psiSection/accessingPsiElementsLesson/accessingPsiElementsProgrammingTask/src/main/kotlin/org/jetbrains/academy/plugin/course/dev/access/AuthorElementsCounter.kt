package org.jetbrains.academy.plugin.course.dev.access

import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction

fun authorCountKtClasses(psiFile: PsiFile) =
    PsiTreeUtil.findChildrenOfType(psiFile, KtClass::class.java).toList().size

fun authorCountKtFunctions(psiFile: PsiFile) =
    PsiTreeUtil.findChildrenOfType(psiFile, KtNamedFunction::class.java).toList().size
