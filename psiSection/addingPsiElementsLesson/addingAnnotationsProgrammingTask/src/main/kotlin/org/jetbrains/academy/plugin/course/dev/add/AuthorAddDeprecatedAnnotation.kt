package org.jetbrains.academy.plugin.course.dev.add

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtPsiFactory


private fun authorAddDeprecatedAnnotation(ktFunction: KtFunction, project: Project) {
    val factory = KtPsiFactory(project)
    val deprecatedAnnotation = factory.createAnnotationEntry("@Deprecated(\"This method is deprecated\")")
    ktFunction.addAnnotationEntry(deprecatedAnnotation)
}

private fun authorHasDeprecatedAnnotation(ktFunction: KtFunction): Boolean {
    return ktFunction.annotationEntries.any {
        it.shortName?.asString() == "Deprecated"
    }
}

fun authorAddDeprecatedAnnotations(psiFile: PsiFile) {
    val project = psiFile.project
    WriteCommandAction.runWriteCommandAction(project) {
        val ktFunctions = PsiTreeUtil.findChildrenOfType(psiFile, KtFunction::class.java)
        for (ktFunction in ktFunctions) {
            if (!authorHasDeprecatedAnnotation(ktFunction)) {
                authorAddDeprecatedAnnotation(ktFunction, project)
            }
        }
    }
}