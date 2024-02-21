package org.jetbrains.academy.plugin.course.dev.project.ui

import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction

class InsertDataClassAction : BaseDataClassAction() {
    override fun executeAdditionalActions(ktFunction: KtNamedFunction, dataClassName: String, psiFile: KtFile) {
        // No additional actions required
    }
}
