package org.jetbrains.academy.plugin.course.dev.project.ui


import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import org.jetbrains.academy.plugin.course.dev.project.extractFunctionArguments

import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction

class RefactorDataClassAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        val psiFile = e.getData(CommonDataKeys.PSI_FILE) as? KtFile

        if (project == null || editor == null || psiFile == null) return

        val caret = editor.caretModel.currentCaret
        val element = psiFile.findElementAt(caret.offset)

        val ktFunction = element?.parent as? KtNamedFunction ?: return

        val arguments = extractFunctionArguments(ktFunction)

        val refactorPanelFactory = RefactorPanelFactory.getInstance()
        if (refactorPanelFactory != null) {
            val data = arguments.map { arrayOf(it.typeReference?.text ?: "", it.name) }
            refactorPanelFactory.updateTable(data)

        }

        /* Update your tool window with the extracted information
        val toolWindow = ToolWindowManager.getInstance(project).getToolWindow("RefactorPanel")
        val contentManager = toolWindow?.contentManager
        val contents = contentManager?.contents // Assuming you have a single content in the tool window

        if (contents != null) {
            for (content in contents) {
                if (content != null && content.component is JBTextArea) {
                    val textArea = content.component as JBTextArea
                    textArea.text = arguments.joinToString("\n") { "${it.typeReference?.text} ${it.name}" }
                }
            }
        } */
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
