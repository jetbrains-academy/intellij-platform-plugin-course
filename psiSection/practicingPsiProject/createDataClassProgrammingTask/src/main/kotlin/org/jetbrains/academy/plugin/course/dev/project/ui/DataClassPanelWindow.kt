package org.jetbrains.academy.plugin.course.dev.project.ui

import com.intellij.openapi.util.Disposer
import com.intellij.ui.jcef.JBCefBrowser
import javax.swing.JComponent

class DataClassPanelWindow(service: DataClassPanelService) {
    private val windowBrowser: JBCefBrowser = JBCefBrowser()

    val jComponent: JComponent
        get() = windowBrowser.component

    init {
        // TODO load the panel's content
        Disposer.register(service, windowBrowser)
    }
}
