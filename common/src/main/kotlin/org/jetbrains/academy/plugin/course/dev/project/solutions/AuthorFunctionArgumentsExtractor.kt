package org.jetbrains.academy.plugin.course.dev.project.solutions

import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtParameter

fun authorExtractFunctionArguments(ktNamedFunction: KtNamedFunction): List<KtParameter> {
    return ktNamedFunction.valueParameters
}
