package org.jetbrains.academy.plugin.course.dev.ui

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.WriteCommandAction
import org.jetbrains.academy.kotlin.template.safeRunStudentCode
import org.jetbrains.academy.plugin.course.dev.edit.sortMethods
import org.jetbrains.academy.ui.psi.BaseUiAction
import org.jetbrains.kotlin.psi.KtClass

class SortMethodsAction : BaseUiAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val element = getCurrentElementFromEditor(e) ?: return
        val ktClass = element.parent as? KtClass ?: return

        WriteCommandAction.runWriteCommandAction(e.project) {
            // Execute your sorting logic here
            safeRunStudentCode {
                sortMethods(ktClass)
            }
        }
    }

    override fun update(event: AnActionEvent) {
        updateEditor(event) { it is KtClass }
    }
}
