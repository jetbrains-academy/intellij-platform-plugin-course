package org.jetbrains.academy.plugin.course.dev.ui

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class PsiElementCounterPanelService : Disposable {
    val demoWindow = PsiElementCounterPanelWindow(this)
    override fun dispose() {
        logger<Project>().info("Jcef window service was disposed")
    }
}
