package org.jetbrains.academy.plugin.course.dev.project.ui

import org.jetbrains.academy.plugin.course.dev.project.replaceFunctionArguments
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction

class ArgumentsReplacementAction : BaseDataClassAction() {
    override fun executeAdditionalActions(ktFunction: KtNamedFunction, dataClassName: String, psiFile: KtFile) {
        replaceFunctionArguments(ktFunction, dataClassName)
    }
}