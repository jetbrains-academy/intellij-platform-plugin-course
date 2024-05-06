package org.jetbrains.academy.plugin.course.dev.project

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.*


fun authorReplaceFunctionCallArgumentsWithDataClass(
    project: Project,
    psiFile: KtFile,
    functionName: KtNamedFunction,
    dataClassName: String
) {
    // Search for usages of the function in the provided file
    val functionCalls = PsiTreeUtil.collectElementsOfType(psiFile, KtCallExpression::class.java)
        .filter { functionName.name?.let { it1 -> it.calleeExpression?.textMatches(it1) } == true }

    functionCalls.forEach { callExpression ->
        val argumentList = callExpression.valueArgumentList
        val arguments = argumentList?.arguments

        if (!arguments.isNullOrEmpty()) {
            // Construct the data class creation expression with arguments
            val newDataClassCreation = authorCreateDataClassCreationExpression(arguments, dataClassName)

            // Replace the entire argument list with the new data class creation expression
            val factory = KtPsiFactory(project)
            val newArgumentList = factory.createCallArguments("($newDataClassCreation)")
            WriteCommandAction.runWriteCommandAction(project) {
                argumentList.replace(newArgumentList)
            }
        }
    }
}

// Helper function to create the data class creation expression
private fun authorCreateDataClassCreationExpression(
    arguments: List<KtValueArgument>,
    dataClassName: String
): String {
    val argumentExpressions = arguments.joinToString(separator = ", ") { it.text }
    return "$dataClassName($argumentExpressions)"
}
