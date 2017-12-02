package basics

import org.apache.commons.lang3.StringUtils
import org.intellij.lang.annotations.Language

fun main(args: Array<String>) {
    val streetNumber = 10
    val streetName = "Magic Street"

    println("I live at $streetNumber, $streetName")

    for (a in 100 downTo 0 step 19) {
        println(a)
    }

    val capitals
            = listOf("London","Rome", "Spain").map { capital -> StringUtils.swapCase(capital) }
    println(capitals)

    checkName(null)
    checkName("Tom")

    val someValue : String? = null

    val nameChecker : String? =    when (someValue) {
        null -> "NPE"
        else -> {
            someValue
        }
    }

    println(nameChecker)

    @Language("RegExp")
    val emailPattern=Regex(pattern = "^([0-9A-Za-z]+)@gmail.com$")

    println(emailPattern.find("abc123@gmail.com")?.groupValues?.get(1))

}

fun checkName(name: String?) {
    when (name) {
        null -> println("NPE")
        else -> {
            println("$name")
        }
    }

}