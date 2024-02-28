package org.jetbrains.academy.plugin.course.dev.project

import org.jetbrains.kotlin.psi.KtParameter

fun authorCreateParameterListString(parameters: List<KtParameter>) =
    parameters.joinToString(separator = ",${System.lineSeparator()}") { param ->
        val paramName = param.name ?: error("Parameter name cannot be null.")
        val paramType = param.typeReference?.text ?: error("Parameter type cannot be determined for '$paramName'.")
        "    val $paramName: $paramType"
    }

fun authorCreateDataClass(className: String = "DataClass", parameters: List<KtParameter>) = """
        |data class $className(
        |${authorCreateParameterListString(parameters)}
        |)
    """.trimMargin()
