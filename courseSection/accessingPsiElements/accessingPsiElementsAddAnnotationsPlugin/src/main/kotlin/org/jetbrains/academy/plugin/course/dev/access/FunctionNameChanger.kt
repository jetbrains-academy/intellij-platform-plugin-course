package org.jetbrains.academy.plugin.course.dev.access

import com.intellij.openapi.command.WriteCommandAction
import org.jetbrains.kotlin.psi.KtNamedFunction

fun editFunctionName(function: KtNamedFunction, newName: String) {
    val project = function.project

    WriteCommandAction.runWriteCommandAction(project) {
        function.setName(newName)
    }
}
