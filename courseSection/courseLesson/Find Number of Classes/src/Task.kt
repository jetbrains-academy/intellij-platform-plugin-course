import com.intellij.openapi.vfs.VirtualFile

class FileAnalyzer(val project: Project) {

    private val psiManager = PsiManager.getInstance(project)

    fun classCounter(file: VirtualFile): Int {
        // get psiFile from virtualFile
        val classes = // get classes from psiFile
        return classes
    }
}