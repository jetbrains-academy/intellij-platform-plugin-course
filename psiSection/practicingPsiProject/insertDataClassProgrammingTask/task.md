**Your Objective**: 

Implement the `insertDataClass` function, which takes the generated data class string and a `PsiFile` 
(representing the current Kotlin file in the IntelliJ IDEA environment) as arguments.

It should insert the data class into the `PsiFile`, effectively adding a new data class definition to your project.

<div class="hint" title="Create new data class">

Utilize `KtPsiFactory` to create a `PSI element` that represents the data class. 
You will need to pass the data class definition as a string to `KtPsiFactory.createClass`.
</div>

**Run Plugin**

To see your implemented methods in action within a UI, run the following command in your project's root directory:

* ` ./gradlew :psiSection-practicingPsiProject-task:runIde
  ` on Linux or MacOS.
* ` gradlew :psiSection-practicingPsiProject-task:runIde
  ` on Windows.
