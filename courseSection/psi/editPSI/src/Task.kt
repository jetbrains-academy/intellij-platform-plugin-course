import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtNamedFunction


fun editFunctionName(psiFile: PsiFile, currentName: String, newName: String) {
    val project = psiFile.project

    WriteCommandAction.runWriteCommandAction(project) {
        val functions = PsiTreeUtil.findChildrenOfType(psiFile, KtNamedFunction::class.java)
        for (function in functions) {
            if (function.name == currentName) {
                function.setName(newName)
            }
        }
    }
}