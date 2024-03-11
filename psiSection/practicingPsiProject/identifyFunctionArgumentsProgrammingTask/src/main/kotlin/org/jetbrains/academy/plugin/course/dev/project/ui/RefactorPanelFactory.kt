package org.jetbrains.academy.plugin.course.dev.project.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.table.JBTable
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Container
import javax.swing.*
import javax.swing.table.DefaultTableModel

class RefactorPanelFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val panel = JPanel(BorderLayout())
        val columnNames = arrayOf("Parameter Type", "Name")

        val tableModel = DefaultTableModel(columnNames, 0)
        val table = JBTable(tableModel)
        table.name = "RefactorTable"

        val scrollPane = JBScrollPane(table)
        panel.add(scrollPane, BorderLayout.CENTER)

        val contentFactory = ContentFactory.getInstance()
        val content = contentFactory.createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
    }
}

fun updateRefactorTable(project: Project, data: List<Array<String?>>) {
    val toolWindow = ToolWindowManager.getInstance(project).getToolWindow("RefactorPanel") ?: return
    val content = toolWindow.contentManager.contents.firstOrNull() ?: return

    val table = findTable(content.component, "RefactorTable")
    if (table != null) {
        SwingUtilities.invokeLater {
            // Assuming table is correctly found and is a JBTable
            val model = table.model as DefaultTableModel
            model.rowCount = 0
            data.forEach { rowData ->
                model.addRow(rowData)
            }
        }
    }
}

private fun findTable(component: Component, name: String): JBTable? {
    if (component is JBTable && component.name == name) {
        return component
    }
    if (component is Container) {
        for (comp in component.components) {
            val table = findTable(comp, name)
            if (table != null) return table
        }
    }
    return null
}
