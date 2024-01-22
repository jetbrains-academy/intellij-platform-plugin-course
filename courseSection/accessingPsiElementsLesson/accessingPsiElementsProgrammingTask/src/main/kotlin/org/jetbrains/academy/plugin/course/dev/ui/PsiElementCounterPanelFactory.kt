package org.jetbrains.academy.plugin.course.dev.ui

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.psi.PsiDocumentManager
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.table.JBTable
import com.intellij.util.ui.JBUI
import org.jetbrains.academy.plugin.course.dev.access.countKtClasses
import org.jetbrains.academy.plugin.course.dev.access.authorCountKtClasses
import org.jetbrains.academy.plugin.course.dev.access.authorCountKtFunctions
import org.jetbrains.academy.plugin.course.dev.access.countKtFunctions
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.table.DefaultTableModel

class PsiElementCounterPanelFactory : ToolWindowFactory {
    companion object {
        private const val DEMO_COUNTER_NOTIFICATION = "PsiElementCounter"
    }


    // TODO use UI bundle instead of a string here?
    private val runTaskMethodButton = JButton("Run task function").apply {
        isBorderPainted = false
        isVisible = true
    }

    lateinit var demoWindow: PsiElementCounterPanelWindow
    private val tableModel = DefaultTableModel()
    private val resultsTable = JBTable(tableModel)

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        demoWindow = project.getService(PsiElementCounterPanelService::class.java).demoWindow
        demoWindow.jComponent.size = JBUI.size(toolWindow.component.width, toolWindow.component.height)

        tableModel.addColumn("PSI Element")
        tableModel.addColumn("Your Output")
        tableModel.addColumn("Expected Output")

        val buttonPanel = JBPanel<JBPanel<*>>(FlowLayout()).apply {
            add(runTaskMethodButton)
        }
        // Wrapping the resultsTable in a JScrollPane
        val scrollPane = JBScrollPane(resultsTable)
        scrollPane.preferredSize = JBUI.size(400, 100) // Set preferred size for scrollPane

        val panel = JBPanel<JBPanel<*>>(BorderLayout()).apply {
            add(buttonPanel, BorderLayout.SOUTH)
            add(scrollPane, BorderLayout.CENTER)
        }
        toolWindow.component.add(panel)

        // TODO add another listener? This example as a demo for now
        runTaskMethodButton.setListener {
            NotificationGroupManager.getInstance().getNotificationGroup(DEMO_COUNTER_NOTIFICATION)
                .createNotification("This panel demonstrates how the plugin works. The current task is a theory task, the plugin does nothing.", NotificationType.INFORMATION)
                .notify(project)
        }

        runTaskMethodButton.addActionListener {
            // Creating a virtual file with Kotlin content
            val editor = FileEditorManager.getInstance(project).selectedTextEditor
            val document = editor?.document

            val psiFile = document?.let { PsiDocumentManager.getInstance(project).getPsiFile(it) }

            psiFile?.let {
                val results = mutableListOf<Array<String>>()

                // Calculate class and function counts, catching exceptions
                val classResult = try { countKtClasses(it).toString() } catch (e: Exception) { "Error" }
                val functionResult = try { countKtFunctions(it).toString() } catch (e: Exception) { "Error" }

                val classAuthorResult = authorCountKtClasses(it).toString()
                val functionAuthorResult = authorCountKtFunctions(it).toString()

                results.add(arrayOf("Class", classResult, classAuthorResult))
                results.add(arrayOf("Function", functionResult, functionAuthorResult))

                tableModel.setRowCount(0)
                results.forEach { tableModel.addRow(it) }
            }
        }
    }

    private fun JButton.setListener(listener: ActionListener) {
        actionListeners.forEach {
            removeActionListener(it)
        }
        addActionListener(listener)
    }

}
