package org.jetbrains.academy.ui.psi

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import org.jetbrains.kotlin.psi.KtFile

abstract class BaseUiAction : AnAction() {
    override fun getActionUpdateThread() = ActionUpdateThread.BGT
    fun getCurrentElementFromEditor(e: AnActionEvent): PsiElement? {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        val psiFile = e.getData(CommonDataKeys.PSI_FILE) as? KtFile
        if (project == null || editor == null || psiFile == null) return null

        return psiFile.findElementAt(editor.caretModel.currentCaret.offset)
    }

    fun updateEditor(event: AnActionEvent, isApplicable: (PsiElement?) -> Boolean) {
        val presentation = event.presentation
        presentation.isEnabledAndVisible = false

        val editor = event.getData(CommonDataKeys.EDITOR) ?: return
        val psiFile = event.getData(CommonDataKeys.PSI_FILE) ?: return
        val elementAtCaret = psiFile.findElementAt(editor.caretModel.offset)

        if (isApplicable(elementAtCaret?.parent)) {
            presentation.isEnabledAndVisible = true
        }
    }
}
