package org.jetbrains.academy.plugin.course.dev.add

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.asJava.classes.looksLikeDeprecated
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtPsiFactory


private fun addDeprecatedAnnotation(ktFunction: KtFunction, project: Project) {
    val factory = KtPsiFactory(project)
    val deprecatedAnnotation = factory.createAnnotationEntry("@Deprecated(\"This method is deprecated\")")
    ktFunction.addAnnotationEntry(deprecatedAnnotation)
}

private fun hasDeprecatedAnnotation(ktFunction: KtFunction): Boolean {
    return ktFunction.annotationEntries.any {
        it.shortName?.asString() == "Deprecated"
    }
}

fun addDeprecatedAnnotations(psiFile: PsiFile) {
    val project = psiFile.project
    WriteCommandAction.runWriteCommandAction(project) {
        val ktFunctions = PsiTreeUtil.findChildrenOfType(psiFile, KtFunction::class.java)
        for (ktFunction in ktFunctions) {
            if (!hasDeprecatedAnnotation(ktFunction)) {
                addDeprecatedAnnotation(ktFunction, project)
            }
        }
    }
}
