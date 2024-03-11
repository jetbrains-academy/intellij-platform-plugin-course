package org.jetbrains.academy.plugin.course.dev.project.ui

import com.intellij.openapi.project.Project
import org.jetbrains.academy.plugin.course.dev.project.*
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction

class VariablesReplacementAction : BaseDataClassAction() {
    override fun executeAdditionalActions(
        ktFunction: KtNamedFunction,
        dataClassName: String,
        psiFile: KtFile,
        project: Project
    ) {
        refactorFunctionBody(ktFunction)
        replaceFunctionArguments(ktFunction, dataClassName)
        replaceFunctionCallArgumentsWithDataClass(project, psiFile, ktFunction, dataClassName)
    }
}
