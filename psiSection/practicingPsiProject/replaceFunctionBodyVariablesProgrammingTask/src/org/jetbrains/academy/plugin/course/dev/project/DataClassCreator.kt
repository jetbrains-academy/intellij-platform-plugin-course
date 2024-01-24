package org.jetbrains.academy.plugin.course.dev.project

import org.jetbrains.kotlin.psi.KtParameter

fun createDataClass(parameters: List<KtParameter>): String {
    val className = "DataClass"

    var dataClassStr = "data class $className(\n"

    // Iterate over parameters to build the constructor
    parameters.forEachIndexed { index, param ->
        val paramName = param.name ?: "param$index"
        val paramType = param.typeReference?.text ?: "Any"

        dataClassStr += "    val $paramName: $paramType"

        // Add a comma except for the last parameter
        if (index < parameters.size - 1) {
            dataClassStr += ",\n"
        }
    }

    dataClassStr += "\n)"

    return dataClassStr
}