package org.jetbrains.academy.plugin.course.dev.project.ui


import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import org.jetbrains.academy.kotlin.template.safeRunStudentCode
import org.jetbrains.academy.plugin.course.dev.project.createDataClass
import org.jetbrains.academy.plugin.course.dev.project.extractFunctionArguments
import org.jetbrains.academy.ui.psi.BaseUiAction
import org.jetbrains.kotlin.psi.KtNamedFunction

class CreateDataClassAction : BaseUiAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val element = getCurrentElementFromEditor(e) ?: return
        val dataClassName = Messages.showInputDialog(
            e.project,
            "Enter the name of the data class:",
            "Create Data Class",
            Messages.getQuestionIcon()
        )

        if (dataClassName.isNullOrEmpty()) return

        val ktFunction = element.parent as? KtNamedFunction ?: return

        safeRunStudentCode {
            val dataClass = createDataClass(dataClassName, extractFunctionArguments(ktFunction))
            e.project?.let { updateTextArea(it, dataClass) }
        }
    }

    override fun update(event: AnActionEvent) {
        updateEditor(event) { it is KtNamedFunction }
    }
}
