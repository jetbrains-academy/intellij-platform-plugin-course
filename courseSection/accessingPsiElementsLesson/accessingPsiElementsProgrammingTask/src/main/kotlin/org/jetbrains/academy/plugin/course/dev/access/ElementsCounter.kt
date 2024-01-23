package org.jetbrains.academy.plugin.course.dev.access

import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction

fun countKtClasses(psiFile: PsiFile) =
    PsiTreeUtil.findChildrenOfType(psiFile, KtClass::class.java).toList().size

fun countKtFunctions(psiFile: PsiFile) =
    PsiTreeUtil.findChildrenOfType(psiFile, KtNamedFunction::class.java).toList().size
