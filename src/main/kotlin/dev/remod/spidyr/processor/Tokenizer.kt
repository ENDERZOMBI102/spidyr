package dev.remod.spidyr.processor

import dev.remod.spidyr.immutable
import dev.remod.spidyr.types.Symbol
import dev.remod.spidyr.types.Token
import dev.remod.spidyr.types.TokenType

object Tokenizer {
    private val access = arrayOf("in","ns")
    private val end = arrayOf("ret","brk","cont")
    private val logic = arrayOf("if","el","whl","for")

    fun tokenize( file: String, input: List<String> ): List<Token> {
        val out = ArrayList<Token?>()
        var line = 1
        var col = 1

        for ( str in input ) {
            val symbol = Symbol.getNameFromValue(str)
            // more direct way to add a token to the list
            out += if ( symbol != null )
                Token( TokenType.Symbol, symbol, file=file, line=line, col=col )
            else if ( str.toFloatOrNull() != null )
                Token( TokenType.Number, value=str, file=file, line=line, col=col )
            else if ( str == ";" ) {
                Token( TokenType.Newline, file=file, line=line++, col=col )
            } else if ( str in logic )
                Token( TokenType.Logic, value=str, file=file, line=line, col=col )
            else if ( str in access )
                Token( TokenType.Access, value=str, file=file, line=line, col=col )
            else if ( str in end )
                Token( TokenType.End, value=str, file=file, line=line, col=col )
            else if ( str == "\n" ) {
                line++
                col = 0
                null
            } else
                Token( TokenType.Name, value=str, file=file, line=line, col=col )
            col += str.length
        }

        return ( out.filterNotNull() as MutableList ).immutable()
    }
}
