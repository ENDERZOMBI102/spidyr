package dev.remod.spidyr

import dev.remod.spidyr.exceptions.InvalidNameException

object StringParser {
    val symbols = "(){}[]+-=*/!:|;."
    val whitespace = Regex("\\s")
    val letters = Regex("[a-zA-Z]")
    val numbers = Regex("[0-9]")

    fun parse(input: String): Array<String> {
        val out = ArrayList<String>()
        var str = ""
        for (i in 0..input.length) {
            val char = input[i].toString()
            if (symbols.indexOf(char) != -1) {
                if (str.isNotEmpty())
                    out.add(str)
                out.add(char)
                str = ""
            }
            if (whitespace.matches(char)) {
                if (str.isNotEmpty())
                    out.add(str)
                str = ""
            }
            if (letters.matches(char)) {
                if (str.isNotEmpty() && numbers.matches(str[0].toString()))
                    throw InvalidNameException()
                str += char
            }
            if (numbers.matches(char))
                str += char
        }

        return out.toTypedArray()
    }
}