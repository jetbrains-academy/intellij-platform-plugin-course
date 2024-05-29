**Your Objective:**

Implement the `refactorFunctionBody` function to modify the body of a given Kotlin function (`KtNamedFunction`) 
so it accesses the original parameters through the new data class instance named **data**.

<div class="hint" title="Navigating the PSI Tree">

Familiarize yourself with the `collectDescendantsOfType` function for traversing the PSI tree.
</div>

**Run Plugin**

To see your implemented methods in action within a UI, run the following command in your project's root directory:

* ` ./gradlew :psiSection-practicingPsiProject-task:runIde
  ` on Linux or MacOS.
* ` gradlew :psiSection-practicingPsiProject-task:runIde
  ` on Windows.
