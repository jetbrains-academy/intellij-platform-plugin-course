package org.jetbrains.academy.plugin.course.dev.project

import org.jetbrains.kotlin.psi.KtParameter

fun createParameterListString(parameters: List<KtParameter>): String {
    return parameters.joinToString(separator = ",\n") { param ->
        val paramName = param.name ?: throw IllegalArgumentException("Parameter name cannot be null.")
        val paramType = param.typeReference?.text ?: throw IllegalArgumentException("Parameter type cannot be determined for '$paramName'.")
        "    val $paramName: $paramType"
    }
}

fun createDataClass(className: String = "DataClass", parameters: List<KtParameter>): String {
    // Generate the parameter list string
    val parametersStr = createParameterListString(parameters)

    // Construct the data class string using the parameters string
    return """
        |data class $className(
        |$parametersStr
        |)
    """.trimMargin()
}
