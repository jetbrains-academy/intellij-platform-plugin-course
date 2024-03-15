package org.jetbrains.academy.plugin.course.dev.edit

import com.intellij.openapi.command.WriteCommandAction
import org.jetbrains.kotlin.psi.KtNamedFunction

fun editFunctionName(function: KtNamedFunction, newName: String) {
    WriteCommandAction.runWriteCommandAction(function.project) {
        function.setName(newName)
    }
}

fun functionNameSnakeToCamelCase(function: KtNamedFunction) {
    val camelCaseFunctionName = function.name?.snakeToCamelCase() ?: return
    editFunctionName(function, camelCaseFunctionName)
}

fun String.snakeToCamelCase(): String {
    return this.split('_').withIndex().joinToString("") { (index, it) ->
        if (index == 0) it.lowercase() else it.lowercase().replaceFirstChar(Char::titlecase)
    }
}
