package org.jetbrains.academy.plugin.course.dev.access

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiType
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.TypeConversionUtil

private fun addOverrideAnnotation(psiMethod: PsiMethod, project: Project) {
    TODO("Not implemented yet")
}

fun addAnnotations(psiFile: PsiFile){
    val project = psiFile.project
    WriteCommandAction.runWriteCommandAction(project) {
        val psiMethods = PsiTreeUtil.findChildrenOfType(psiFile, PsiMethod::class.java)

        for (psiMethod in psiMethods) {
            if (shouldAddOverrideAnnotation(psiMethod)) {
                addOverrideAnnotation(psiMethod, project)
            }
        }
    }
}

private fun shouldAddOverrideAnnotation(psiMethod: PsiMethod): Boolean {
    // Logic to determine if this method overrides a method from its superclass
    val containingClass = psiMethod.containingClass ?: return false

    val superClasses = containingClass.supers
    val methodName = psiMethod.name
    val parameterTypes = psiMethod.parameterList.parameters.map { it.type }

    for (superClass in superClasses) {
        val superMethods = superClass.methods
        for (superMethod in superMethods) {
            if (superMethod.name == methodName
                && superMethod.parameterList.parameters.map { it.type } == parameterTypes
                && isReturnTypeCompatible(superMethod.returnType, psiMethod.returnType)
            ) {
                return true
            }
        }
    }
    return false
}

private fun isReturnTypeCompatible(superReturnType: PsiType?, subReturnType: PsiType?): Boolean {
    if (superReturnType == null || subReturnType == null) return false
    return TypeConversionUtil.isAssignable(superReturnType, subReturnType)
}
