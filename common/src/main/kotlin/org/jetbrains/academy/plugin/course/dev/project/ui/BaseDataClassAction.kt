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
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction

abstract class BaseDataClassAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val psiFile = e.getData(CommonDataKeys.PSI_FILE) as? KtFile ?: return

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
        val presentation = event.presentation
        presentation.isEnabledAndVisible = false

        val editor = event.getData(CommonDataKeys.EDITOR) ?: return
        val psiFile = event.getData(CommonDataKeys.PSI_FILE) ?: return
        val elementAtCaret = psiFile.findElementAt(editor.caretModel.offset)

        if (elementAtCaret?.parent is KtNamedFunction) {
            presentation.isEnabledAndVisible = true
        }
    }
}
