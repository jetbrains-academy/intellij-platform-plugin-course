package org.jetbrains.academy.plugin.course.dev.project

import com.intellij.openapi.command.WriteCommandAction
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.psi.KtReferenceExpression
import org.jetbrains.kotlin.psi.psiUtil.collectDescendantsOfType

fun authorCreateAccessExpression(ktPsiFactory: KtPsiFactory, dataClassName: String, paramName: String): KtExpression =
    ktPsiFactory.createExpression("$dataClassName.$paramName")

fun authorRefactorFunctionBody(ktNamedFunction: KtNamedFunction) {
    val project = ktNamedFunction.project

    val originalParameters = extractFunctionArguments(ktNamedFunction).mapNotNull { it.name }

    WriteCommandAction.runWriteCommandAction(project) {
        val functionBody = ktNamedFunction.bodyExpression ?: return@runWriteCommandAction
        val ktPsiFactory = KtPsiFactory(project)

        // Iterate through each reference to the original parameters in the function body
        originalParameters.forEach { paramName ->
            val references = functionBody.collectDescendantsOfType<KtReferenceExpression> {
                it.text == paramName
            }

            references.forEach { reference ->
                // Replace the reference with "data.paramName"
                val newExpression = authorCreateAccessExpression(ktPsiFactory, "data", paramName)
                reference.replace(newExpression)
            }
        }
    }
}
