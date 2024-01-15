package org.jetbrains.academy.plugin.course.dev.ui

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.WriteAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.psi.PsiManager
import com.intellij.testFramework.LightVirtualFile
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.table.JBTable
import com.intellij.util.ui.JBUI
import org.jetbrains.academy.plugin.course.dev.access.countKtFunctions
import org.jetbrains.kotlin.idea.KotlinLanguage
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.table.DefaultTableModel

class DemoPanelFactory : ToolWindowFactory {
    companion object {
        private const val DEMO_PLUGIN_NOTIFICATION = "IdeDevCourseDemo"
    }


    // TODO use UI bundle instead of a string here?
    private val runTaskMethodButton = JButton("Run task function").apply {
        isBorderPainted = false
        isVisible = true
    }

    lateinit var demoWindow: DemoPanelWindow
    private val tableModel = DefaultTableModel()
    private val resultsTable = JBTable(tableModel)
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        demoWindow = project.getService(DemoPanelService::class.java).demoWindow
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
            NotificationGroupManager.getInstance().getNotificationGroup(DEMO_PLUGIN_NOTIFICATION)
                .createNotification("This panel demonstrates how the plugin works. The current task is a theory task, the plugin does nothing.", NotificationType.INFORMATION)
                .notify(project)
        }

        runTaskMethodButton.addActionListener {
            // Creating a virtual file with Kotlin content
            val tempVfsFile = WriteAction.compute<LightVirtualFile, Exception> {
                LightVirtualFile("MyClass.kt", KotlinLanguage.INSTANCE, """
            fun topLevelFunction1() {
                // Function implementation
            }

            fun topLevelFunction2() {
                // Function implementation
            }
            
            fun topLevelFunction3() {
                // Function implementation
            }
        """.trimIndent())
            }

            // Convert virtual file to PSI file
            val psiFile = PsiManager.getInstance(project).findFile(tempVfsFile)

            // Call your function and get the result
            psiFile?.let {
                val result = countKtFunctions(it)

                // Display the result in the table
                tableModel.setRowCount(0)
                tableModel.addRow(arrayOf("Function", result.toString(), 3))
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
