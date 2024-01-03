package jetbrains.academy.plugin.course.dev.access

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction

fun sortMethods(psiFile: PsiFile) {
    val project = psiFile.project
    WriteCommandAction.runWriteCommandAction(project) {
        val classes = PsiTreeUtil.findChildrenOfType(psiFile, KtClass::class.java)

        for (ktClass in classes){
            val methods = ktClass.declarations.filterIsInstance<KtNamedFunction>()
            val sortedMethods = methods.sortedBy { it.name }.map { it.copy() as KtNamedFunction }

            methods.zip(sortedMethods).forEach { (original, sortedCopy) ->
                original.replace(sortedCopy)
            }
        }
    }
}
