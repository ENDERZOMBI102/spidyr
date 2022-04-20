package dev.remod.spidyr.processor

import dev.remod.spidyr.exceptions.NoSemicolonException
import dev.remod.spidyr.types.Token
import dev.remod.spidyr.types.ast.AstRoot

object AstGenerator {
    var i = 0
    fun generateAst(tokens: Array<Token>): AstRoot {
        var pkg = ""
        if (tokens[i].value == "pkg") {
            i++
            pkg = parsePath(tokens)
        }
        if (tokens[i++].type != "NEWLINE") throw NoSemicolonException()
        val imports = ArrayList<String>()
        while (tokens[i].value == "in") {
            i++
            imports.add(parsePath(tokens))
            if (tokens[i++].type != "NEWLINE") throw NoSemicolonException()
        }

        return AstRoot(
            pkg,imports.toTypedArray()
        )
    }

    fun parsePath(tokens: Array<Token>): String {
        var str = ""

        for (j in i..tokens.size) {
            i = j
            if (tokens[i].type != "NAME" && tokens[i].type != "DOT") break

            if (tokens[i].type == "NAME") str += tokens[i].value
            else str += "."
        }
        return str
    }
}