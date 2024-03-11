package org.jetbrains.academy.plugin.course.dev.project.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.content.ContentFactory
import com.intellij.util.ui.JBUI
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Container
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea

class DataClassPanelFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val panel = JPanel(BorderLayout())
        val sharedTextArea = JBTextArea("Please, use context menu to see the created data class").apply {
            lineWrap = true
            wrapStyleWord = true
            isEditable = false
            margin = JBUI.insets(5) // Optionally add some margin
            name = "DataClassPanelTextArea"
        }
        panel.add(JScrollPane(sharedTextArea), BorderLayout.CENTER)

        // Add the panel to the tool window
        val contentFactory = ContentFactory.getInstance()
        val content = contentFactory.createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
    }
}

fun updateTextArea(project: Project, dataClass: String) {
    val toolWindow = ToolWindowManager.getInstance(project).getToolWindow("DataClassPanel") ?: return
    val content = toolWindow.contentManager.contents.firstOrNull()
    val component = content?.let { findTextArea(it.component) }

    if (component != null) {
        component.text = dataClass
    }
}

private fun findTextArea(component: Component): JTextArea? {
    if (component is JTextArea && component.name == "DataClassPanelTextArea") {
        return component
    }
    if (component is Container) {
        for (comp in component.components) {
            val textArea = findTextArea(comp)
            if (textArea != null) return textArea
        }
    }
    return null
}
