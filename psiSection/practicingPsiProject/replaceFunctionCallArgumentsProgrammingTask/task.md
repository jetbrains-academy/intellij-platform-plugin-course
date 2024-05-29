Congratulations on reaching the final step of your plugin development project!

You have successfully refactored a function to use a data class for its parameters and modified the function body to access these parameters through the data class.

This task involves updating each call to the refactored function, replacing its original list of arguments with a single argument: an instance of the data class.

**Your Objective:**

Implement the `replaceFunctionCallArgumentsWithDataClass` function. This function scans a Kotlin file for calls to the refactored function and replaces the existing arguments in each call with a new instance of the data class, properly populated with the original arguments.

<div class="hint" title="Finding Function Calls">

Leverage `PsiTreeUtil.collectElementsOfType` to traverse the PSI and identify function call expressions 
that match the specified function.
</div>

**Run Plugin**

To see your implemented methods in action within a UI, run the following command in your project's root directory:

* ` ./gradlew :psiSection-practicingPsiProject-task:runIde
  ` on Linux or MacOS.
* ` gradlew :psiSection-practicingPsiProject-task:runIde
  ` on Windows.
