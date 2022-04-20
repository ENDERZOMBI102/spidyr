package dev.remod.spidyr.processor

import dev.remod.spidyr.types.Symbol
import dev.remod.spidyr.types.Token

object Tokenizer {
    val access = arrayOf("import","export","namespace")
    val declare = arrayOf("func", "var", "imp", "ext")
    val end = arrayOf("ret","brk","cont")
    val logic = arrayOf("if","else","while","for")
    val defaultTypes = arrayOf("int", "lng", "byt", "str", "flt", "dbl", "bl", "srt")

    fun tokenize(input: Array<String>): Array<Token> {
        val out = ArrayList<Token>()

        for (str in input) {
            val symbol = Symbol.getNameFromValue(str)
            if (str.toFloatOrNull() != null)
                out.add(Token(
                    "number",
                    str
                ))
            else if (symbol != null)
                out.add(Token(
                    symbol.name
                ))
            else if (str == ";")
                out.add(Token(
                    "newline"
                ))
            else if (declare.indexOf(str) != -1)
                out.add(Token(
                    "declare",
                    str
                ))
            else if (defaultTypes.indexOf(str) != -1)
                out.add(Token(
                    "type",
                    str
                ))
            else if (logic.indexOf(str) != -1)
                out.add(Token(
                    "logic",
                    str
                ))
            else if (access.indexOf(str) != -1)
                out.add(Token(
                    "access",
                    str
                ))
            else if (end.indexOf(str) != -1)
                out.add(Token(
                    "end",
                    str
                ))
            else out.add(Token(
                "name",
                str
            ))
        }

        return out.toTypedArray()
    }
}