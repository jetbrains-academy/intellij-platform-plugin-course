package org.jetbrains.academy.plugin.course.dev.project.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.content.ContentFactory
import com.intellij.util.ui.JBUI
import java.awt.BorderLayout
import javax.swing.*

class DataClassPanelFactory : ToolWindowFactory {
    companion object {
        private var instance: DataClassPanelFactory? = null

        fun getInstance(): DataClassPanelFactory? {
            return instance
        }
    }


    private lateinit var textArea: JTextArea

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val panel = JPanel(BorderLayout())
        textArea = JBTextArea("Please, use context menu to see the created data class").apply {
            lineWrap = true
            wrapStyleWord = true
            isEditable = false
            margin = JBUI.insets(5) // Optionally add some margin
        }
        panel.add(JScrollPane(textArea), BorderLayout.CENTER)

        // Add the panel to the tool window
        val contentFactory = ContentFactory.getInstance()
        val content = contentFactory.createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)

        instance = this
    }

    fun updateTextArea(text: String) {
        SwingUtilities.invokeLater {
            textArea.text = text
        }
    }

}
