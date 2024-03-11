package org.jetbrains.academy.plugin.course.dev.project.ui

import com.intellij.openapi.project.Project
import org.jetbrains.academy.plugin.course.dev.project.replaceFunctionArguments
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction

class ArgumentsReplacementAction : BaseDataClassAction() {
    override fun executeAdditionalActions(ktFunction: KtNamedFunction, dataClassName: String, psiFile: KtFile, project: Project) {
        replaceFunctionArguments(ktFunction, dataClassName)
    }
}
