package org.jetbrains.academy.plugin.course.dev.project

import com.intellij.openapi.command.WriteCommandAction
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtPsiFactory

fun replaceFunctionArguments(ktNamedFunction: KtNamedFunction, dataClassName: String) {
    val project = ktNamedFunction.project
    val factory = KtPsiFactory(project)

    // Create a new parameter of the data class type
    val newDataClassParameter = factory.createParameter("data: $dataClassName")

    WriteCommandAction.runWriteCommandAction(project) {
        ktNamedFunction.valueParameterList?.replace(factory.createParameterList("(${newDataClassParameter.text})"))
    }
}
