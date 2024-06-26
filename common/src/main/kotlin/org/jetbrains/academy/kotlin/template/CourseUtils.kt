package org.jetbrains.academy.kotlin.template

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.StandardCharsets

val newLineSeparator: String = System.lineSeparator()

fun throwInternalCourseError(): Nothing = error("Internal course error!")

fun setSystemIn(input: List<String>? = null) = setSystemIn(input?.joinToString(newLineSeparator))

fun String.replaceLineSeparator() = this.lines().joinToString(newLineSeparator)

fun setSystemIn(input: String? = null) = input?.let {
    System.setIn(it.replaceLineSeparator().byteInputStream())
}

fun setSystemOut(): ByteArrayOutputStream {
    val baos = ByteArrayOutputStream()
    val ps = PrintStream(baos, true, StandardCharsets.UTF_8.name())
    System.setOut(ps)
    return baos
}

fun isSystemInEmpty() = String(System.`in`.readBytes()).isEmpty()

@Suppress("SwallowedException")
fun runMainFunction(mainFunction: () -> Unit, input: String? = null, toAssertSystemIn: Boolean = true): String {
    return try {
        setSystemIn(input)
        val baos = setSystemOut()
        mainFunction()
        if (toAssertSystemIn) {
            assert(isSystemInEmpty()) { "You are asking the user to enter data fewer times than required in the task!" }
        }
        baos.toString("UTF-8").replaceLineSeparator()
    } catch (e: IllegalStateException) {
        val userInput = input?.let { "the user input: $it" } ?: "the empty user input"
        val errorPrefix =
            "Try to run the main function with $userInput, the function must process the input and exit, but the current version of the function"
        if ("Your input is incorrect" in (e.message ?: "")) {
            assert(false) { "$errorPrefix waits more user inputs, it must be fixed." }
        }
        assert(false) { "$errorPrefix throws an unexpected error, please, check the function's implementation." }
        ""
    } catch (e: NotImplementedError) {
        assert(false) { "You call not implemented functions (that use TODO()) inside the main function. Please, don't do it until the task asks it" }
        ""
    }
}

@Suppress("TooGenericExceptionCaught", "SwallowedException")
fun <T> safeRunStudentCode(action: () -> T): String {
    return try {
        action().toString()
    } catch (e: NotImplementedError) {
        "Not implemented"
    } catch (e: Exception) {
        "Error"
    }
}
