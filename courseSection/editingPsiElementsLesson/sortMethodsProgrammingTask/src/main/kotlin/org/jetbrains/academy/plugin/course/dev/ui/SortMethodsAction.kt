package org.jetbrains.academy.plugin.course.dev.ui

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import org.jetbrains.academy.kotlin.template.safeRunStudentCode
import org.jetbrains.academy.plugin.course.dev.edit.sortMethods
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFile

class SortMethodsAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        val psiFile = e.getData(CommonDataKeys.PSI_FILE) as? KtFile

        if (project == null || editor == null || psiFile == null) return

        val caret = editor.caretModel.currentCaret
        val element = psiFile.findElementAt(caret.offset)

        val ktClass = element?.parent as? KtClass ?: return

        WriteCommandAction.runWriteCommandAction(project) {
            // Execute your sorting logic here
            safeRunStudentCode {
                sortMethods(ktClass)
            }
        }
    }

    override fun update(event: AnActionEvent) {
        val presentation = event.presentation
        presentation.isEnabledAndVisible = false

        val editor = event.getData(CommonDataKeys.EDITOR) ?: return
        val psiFile = event.getData(CommonDataKeys.PSI_FILE) ?: return
        val elementAtCaret = psiFile.findElementAt(editor.caretModel.offset)

        if (elementAtCaret?.parent is KtClass) {
            presentation.isEnabledAndVisible = true
        }
    }
}
