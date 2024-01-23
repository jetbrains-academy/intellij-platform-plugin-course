package org.jetbrains.academy.plugin.course.dev.edit

import com.intellij.openapi.command.WriteCommandAction
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction

fun sortMethods(ktClass: KtClass) {
    val project = ktClass.project
    WriteCommandAction.runWriteCommandAction(project) {
        val methods = ktClass.declarations.filterIsInstance<KtNamedFunction>()
        val sortedMethods = methods.sortedBy { it.name }.map { it.copy() as KtNamedFunction }

        methods.zip(sortedMethods).forEach { (original, sortedCopy) ->
            original.replace(sortedCopy)
        }
    }
}
