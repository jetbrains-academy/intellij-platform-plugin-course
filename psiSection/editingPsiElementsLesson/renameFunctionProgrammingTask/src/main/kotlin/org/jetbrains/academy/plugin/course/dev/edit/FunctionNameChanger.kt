package org.jetbrains.academy.plugin.course.dev.edit

import com.intellij.openapi.command.WriteCommandAction
import org.jetbrains.kotlin.psi.KtNamedFunction

fun editFunctionName(function: KtNamedFunction, newName: String) {
    WriteCommandAction.runWriteCommandAction(function.project) {
        function.setName(newName)
    }
}
