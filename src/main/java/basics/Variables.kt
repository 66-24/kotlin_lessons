package basics

import org.apache.commons.lang3.StringUtils

fun main(args: Array<String>) {
    val streetNumber: Int = 10
    val streetName: String = "Magic Street"

    println("I live at $streetNumber, $streetName")

    for (a in 100 downTo 0 step 19) {
        println(a)
    }

    val capitals
            = listOf("London","Rome", "Spain").map { capital -> StringUtils.swapCase(capital) }
    println(capitals)

    checkName(null)
    checkName("Tom")

    var someValue : String? = null

    val nameChecker : String? =    when (someValue) {
        null -> "NPE"
        else -> {
            someValue
        }
    }

    println(nameChecker)
    someValue = "Phew!" //nameChecker bound to older "some value"

    println(nameChecker)

//    @Language("RegExp")
    val emailPattern=Regex("^([0-9A-Za-z]+)@gmail.com$")

    println(emailPattern.find("abc123@gmail.com")?.groupValues?.get(1))

    val jsonString="{\"name\" : \"$someValue\"}"
}

fun checkName(name: String?) {
    when (name) {
        null -> println("NPE")
        else -> {
            println("$name")
        }
    }

}