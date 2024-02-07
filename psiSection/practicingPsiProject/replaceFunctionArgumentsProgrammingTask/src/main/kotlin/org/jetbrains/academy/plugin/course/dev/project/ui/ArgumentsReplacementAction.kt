package org.jetbrains.academy.plugin.course.dev.project.ui

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ui.Messages
import org.jetbrains.academy.plugin.course.dev.project.createDataClass
import org.jetbrains.academy.plugin.course.dev.project.extractFunctionArguments
import org.jetbrains.academy.plugin.course.dev.project.insertDataClass
import org.jetbrains.academy.plugin.course.dev.project.replaceFunctionArguments
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction

class ArgumentsReplacementAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        val psiFile = e.getData(CommonDataKeys.PSI_FILE) as? KtFile

        if (project == null || editor == null || psiFile == null) return

        val dataClassName = Messages.showInputDialog(
            project,
            "Enter the name of the data class:",
            "Create Data Class",
            Messages.getQuestionIcon()
        )

        if (dataClassName.isNullOrEmpty()) return

        val caret = editor.caretModel.currentCaret
        val element = psiFile.findElementAt(caret.offset)

        val ktFunction = element?.parent as? KtNamedFunction ?: return

        val arguments = extractFunctionArguments(ktFunction)

        val dataClass = createDataClass(dataClassName, arguments)

        insertDataClass(dataClass, psiFile)

        replaceFunctionArguments(ktFunction, dataClassName)
    }

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