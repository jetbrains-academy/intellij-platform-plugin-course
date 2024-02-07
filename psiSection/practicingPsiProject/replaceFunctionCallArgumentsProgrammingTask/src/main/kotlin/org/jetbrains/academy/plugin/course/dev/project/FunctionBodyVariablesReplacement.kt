package org.jetbrains.academy.plugin.course.dev.project

import com.intellij.openapi.command.WriteCommandAction
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.psi.KtReferenceExpression
import org.jetbrains.kotlin.psi.psiUtil.collectDescendantsOfType

fun createAccessExpression(ktPsiFactory: KtPsiFactory, dataClassName: String, paramName: String): KtExpression {
    return ktPsiFactory.createExpression("$dataClassName.$paramName")
}

fun refactorFunctionBody(ktNamedFunction: KtNamedFunction) {
    val project = ktNamedFunction.project

    val originalParameters = extractFunctionArguments(ktNamedFunction).mapNotNull { it.name }

    WriteCommandAction.runWriteCommandAction(project) {
        val ktPsiFactory = KtPsiFactory(project)
        val functionBody = ktNamedFunction.bodyExpression ?: return@runWriteCommandAction

        // Iterate through each reference to the original parameters in the function body
        originalParameters.forEach { paramName ->
            val references = functionBody.collectDescendantsOfType<KtReferenceExpression> {
                it.text == paramName
            }

            references.forEach { reference ->
                // Replace the reference with "data.paramName"
                val newExpression = createAccessExpression(ktPsiFactory, "data", paramName)
                reference.replace(newExpression)
            }
        }
    }
}
