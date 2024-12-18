**Your Objective**:

Implement the `replaceFunctionArguments` function to modify the signature of a given Kotlin function (`KtNamedFunction`) 
by replacing all its existing parameters with a single parameter named **data** of the type specified by `dataClassName`.

<div class="hint" title="Creating a New Parameter">

Learn how `KtPsiFactory.createParameter` can be used to create a function parameter with a specific type and name.
</div>

**Run Plugin**

To see your implemented methods in action within a UI, run the following command in your project's root directory:

* ` ./gradlew :psiSection-practicingPsiProject-task:runIde
  ` on Linux or MacOS.
* ` gradlew :psiSection-practicingPsiProject-task:runIde
  ` on Windows.
