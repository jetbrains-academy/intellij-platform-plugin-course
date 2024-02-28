package org.jetbrains.academy.plugin.course.dev.ui

import com.intellij.psi.PsiFile
import org.jetbrains.academy.kotlin.template.safeRunStudentCode
import org.jetbrains.academy.plugin.course.dev.access.authorCountKtClasses
import org.jetbrains.academy.plugin.course.dev.access.authorCountKtFunctions
import org.jetbrains.academy.plugin.course.dev.access.countKtClasses
import org.jetbrains.academy.plugin.course.dev.access.countKtFunctions

enum class Counter(
    val entityName: String,
    val counterFromStudent: (PsiFile) -> Int,
    val counterFromAuthor: (PsiFile) -> Int
) {
    Class("Class", ::countKtClasses, ::authorCountKtClasses),
    Function("Function", ::countKtFunctions, ::authorCountKtFunctions)
    ;

    fun PsiFile.buildTableRow(): Array<String> {
        val studentResult = safeRunStudentCode {
            counterFromStudent(this).toString()
        }
        return arrayOf(entityName, studentResult, counterFromAuthor(this).toString())
    }
}
