package dev.remod.spidyr.processor

import dev.remod.spidyr.exceptions.InvalidNameException
import dev.remod.spidyr.immutable

object StringParser {
    private const val symbols = "(){}[]<>+-=*/!:|;.,"
    private val whitespace = Regex("\\s")
    private val letters = Regex("[a-zA-Z]")
    private val numbers = Regex("[0-9]")

    fun parse(input: String): List<String> {
        val out = ArrayList<String>()
        var str = ""
        for ( element in input ) {
            val char = element.toString()
            if ( char in symbols ) {
                if ( str.isNotEmpty() )
                    out.add(str)
                out.add(char)
                str = ""
            }
            if ( whitespace.matches(char) ) {
                if ( str.isNotEmpty() )
                    out.add(str)
                if ( char == "\n" )
                    out.add( char ) // conserve newlines for tokenizing step
                str = ""
            }
            if ( letters.matches(char) ) {
                if ( str.isNotEmpty() && numbers.matches( str[0].toString() ) )
                    throw InvalidNameException()
                str += char
            }
            if ( numbers.matches(char) )
                str += char
        }

        return out.immutable()
    }
}