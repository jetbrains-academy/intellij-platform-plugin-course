package org.jetbrains.academy.plugin.course.dev.project.ui


import com.intellij.openapi.actionSystem.AnActionEvent
import org.jetbrains.academy.kotlin.template.safeRunStudentCode
import org.jetbrains.academy.plugin.course.dev.project.extractFunctionArguments
import org.jetbrains.academy.ui.psi.BaseUiAction
import org.jetbrains.kotlin.psi.KtNamedFunction

class RefactorDataClassAction : BaseUiAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val element = getCurrentElementFromEditor(e) ?: return
        val ktFunction = element.parent as? KtNamedFunction ?: return

        safeRunStudentCode {
            val arguments = extractFunctionArguments(ktFunction)
            val data = arguments.map { arrayOf(it.typeReference?.text ?: "", it.name) }
            e.project?.let { updateRefactorTable(it, data) }
        }
    }

    override fun update(event: AnActionEvent) {
        updateEditor(event) { it is KtNamedFunction }
    }
}
