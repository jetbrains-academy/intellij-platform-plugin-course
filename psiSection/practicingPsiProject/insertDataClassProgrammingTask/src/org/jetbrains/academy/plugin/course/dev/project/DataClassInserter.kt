package org.jetbrains.academy.plugin.course.dev.project

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.PsiFile
import org.jetbrains.kotlin.psi.KtPsiFactory

fun insertDataClass(dataClass: String, psiFile: PsiFile) {
    val project = psiFile.project
    val ktPsiFactory = KtPsiFactory(project)

    // Create a PsiElement for the data class
    val dataClassElement = ktPsiFactory.createClass(dataClass)

    WriteCommandAction.runWriteCommandAction(project) {
        psiFile.add(dataClassElement)
    }
}