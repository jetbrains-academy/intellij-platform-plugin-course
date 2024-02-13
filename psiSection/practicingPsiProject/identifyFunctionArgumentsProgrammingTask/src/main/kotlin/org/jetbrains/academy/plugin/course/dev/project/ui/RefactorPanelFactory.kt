package org.jetbrains.academy.plugin.course.dev.project.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.table.JBTable
import java.awt.BorderLayout
import javax.swing.*
import javax.swing.table.DefaultTableModel

class RefactorPanelFactory : ToolWindowFactory {
    companion object {
        private var instance: RefactorPanelFactory? = null

        fun getInstance(): RefactorPanelFactory? {
            return instance
        }
    }

    private lateinit var tableModel: DefaultTableModel
    private lateinit var table: JBTable

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val panel = JPanel(BorderLayout())
        // Define column names
        val columnNames = arrayOf("Parameter Type", "Name")

        // Create table model with column names
        tableModel = DefaultTableModel(columnNames, 0)

        // Create JBTable with the table model
        table = JBTable(tableModel)

        // Add the table to a scroll pane
        val scrollPane = JBScrollPane(table)
        panel.add(scrollPane, BorderLayout.CENTER)

        // Add the panel to the tool window
        val contentFactory = ContentFactory.getInstance()
        val content = contentFactory.createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)

        instance = this
    }


    fun updateTable(data: List<Array<String?>>) {
        SwingUtilities.invokeLater {
            // Clear existing data
            tableModel.rowCount = 0

            // Add new rows
            data.forEach { rowData ->
                tableModel.addRow(rowData)
            }
        }
    }

}
