package org.jetbrains.academy.plugin.course.dev.project.ui

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import org.jetbrains.academy.kotlin.template.safeRunStudentCode
import org.jetbrains.academy.plugin.course.dev.project.solutions.authorCreateDataClass
import org.jetbrains.academy.plugin.course.dev.project.solutions.authorExtractFunctionArguments
import org.jetbrains.academy.plugin.course.dev.project.solutions.authorInsertDataClass
import org.jetbrains.academy.ui.psi.BaseUiAction
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction

abstract class BaseDataClassAction : BaseUiAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        val psiFile = e.getData(CommonDataKeys.PSI_FILE) as? KtFile

        // Check all conditions at once to reduce return statements
        if (project == null || editor == null || psiFile == null) return

        val dataClassName = Messages.showInputDialog(project, "Enter the name of the data class:", "Create Data Class", Messages.getQuestionIcon()) ?: return

        val caret = editor.caretModel.currentCaret
        val element = psiFile.findElementAt(caret.offset)
        val ktFunction = element?.parent as? KtNamedFunction ?: return

        safeRunStudentCode {
            val arguments = authorExtractFunctionArguments(ktFunction)
            val dataClass = authorCreateDataClass(dataClassName, arguments)
            authorInsertDataClass(dataClass, psiFile)
            executeAdditionalActions(ktFunction, dataClassName, psiFile, project)
        }
    }

    // Now an abstract method that can be overridden by subclasses to execute additional actions
    abstract fun executeAdditionalActions(ktFunction: KtNamedFunction, dataClassName: String, psiFile: KtFile, project: Project)

    override fun update(event: AnActionEvent) {
        updateEditor(event) { it is KtNamedFunction }
    }
}
