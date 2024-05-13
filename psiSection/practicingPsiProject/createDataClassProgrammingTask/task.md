In this task, you are tasked with implementing a function `createDataClass`, which generates a string representation of a Kotlin data class given a class name and a list of parameters (`List<KtParameter>`).

This string will later be used to insert the data class definition into your Kotlin file programmatically.

**Your Objective**:

Complete the implementation of the `createDataClass` function that constructs and returns a Kotlin data class definition as a string.

This involves two main steps:

* Generate the *Parameter List String*: Use the provided `createParameterListString` function to transform the list of KtParameter objects into a formatted string where each parameter is declared within the data class.

  Each parameter declaration should follow the format: `val paramName: paramType`, and parameters should be separated by commas and line breaks for readability.

* Construct the *Data Class String*: With the parameter list string prepared, construct the final data class definition string.

  The data class should be named according to the className parameter (defaulting to "DataClass" if no name is provided) and include the formatted parameters within its declaration.

**Run Plugin**

To see your implemented methods in action within a UI, run the following command in your project's root directory:

* ` ./gradlew :psiSection-practicingPsiProject-task:runIde
  ` on Linux or MacOS
* ` gradlew :psiSection-practicingPsiProject-task:runIde
  ` on Windows
