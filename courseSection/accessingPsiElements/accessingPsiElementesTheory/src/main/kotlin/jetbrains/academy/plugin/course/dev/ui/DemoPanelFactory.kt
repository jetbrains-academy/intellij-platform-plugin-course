package jetbrains.academy.plugin.course.dev.ui

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBPanel
import com.intellij.util.ui.JBUI
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.event.ActionListener
import javax.swing.JButton

class DemoPanelFactory : ToolWindowFactory {
    companion object {
        private const val DEMO_PLUGIN_NOTIFICATION = "IdeDevCourseDemo"
    }


    // TODO: use UI bundle instead of a string here?
    private val runTaskMethodButton = JButton("Run task function").apply {
        isBorderPainted = false
        isVisible = true
    }

    lateinit var demoWindow: DemoPanelWindow
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        demoWindow = project.getService(DemoPanelService::class.java).demoWindow
        demoWindow.jComponent.size = JBUI.size(toolWindow.component.width, toolWindow.component.height)
        val buttonPanel = JBPanel<JBPanel<*>>(FlowLayout()).apply {
            add(runTaskMethodButton)
        }
        val panel = JBPanel<JBPanel<*>>(BorderLayout()).apply {
            add(demoWindow.jComponent)
            add(buttonPanel, BorderLayout.SOUTH)
        }
        toolWindow.component.add(panel)

        // TODO: add another listener? This example as a demo for now
        runTaskMethodButton.setListener {
            NotificationGroupManager.getInstance().getNotificationGroup(DEMO_PLUGIN_NOTIFICATION)
                .createNotification("This panel demonstrates how the plugin works. The current task is a theory task, the plugin does nothing.", NotificationType.INFORMATION)
                .notify(project)
        }
        // TODO: add an action to the button
    }

    private fun JButton.setListener(listener: ActionListener) {
        actionListeners.forEach {
            removeActionListener(it)
        }
        addActionListener(listener)
    }

}