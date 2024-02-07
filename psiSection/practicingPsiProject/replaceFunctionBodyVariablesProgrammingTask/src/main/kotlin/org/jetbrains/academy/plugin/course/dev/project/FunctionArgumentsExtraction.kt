package org.jetbrains.academy.plugin.course.dev.project

import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtParameter

fun extractFunctionArguments(ktNamedFunction: KtNamedFunction): List<KtParameter> {
    return ktNamedFunction.valueParameters
}
