package org.jetbrains.academy.plugin.course.dev.project

import org.jetbrains.kotlin.psi.KtParameter

fun createParameterListString(parameters: List<KtParameter>) =
    parameters.joinToString(separator = ",${System.lineSeparator()}") { param ->
        val paramName = param.name ?: error("Parameter name cannot be null.")
        val paramType = param.typeReference?.text ?: error("Parameter type cannot be determined for '$paramName'.")
        "    val $paramName: $paramType"
    }

fun createDataClass(className: String = "DataClass", parameters: List<KtParameter>): String {
    // Generate the parameter list string
    val paramsStr = createParameterListString(parameters)

    // Construct the data class string using the parameters string
    return """
        |data class $className(
        |$paramsStr
        |)
    """.trimMargin()
}

