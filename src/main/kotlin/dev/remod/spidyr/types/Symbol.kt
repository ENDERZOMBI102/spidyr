package dev.remod.spidyr.types

enum class Symbol(val value: String) {
    P_OPEN("("),
    P_CLOSE(")"),
    B_OPEN("{"),
    B_CLOSE("}"),
    S_OPEN("["),
    S_CLOSE("]"),
    PLUS("+"),
    MINUS("-"),
    EQUAL("="),
    MULT("*"),
    DIV("/"),
    NOT("!"),
    COLON(":"),
    OR("|"),
    DOT("."),
    COMMA(",")
    ;

    companion object {
        fun getNameFromValue(string: String): Symbol? {
            for (i in values())
                if (i.value == string) return i
            return null
        }
    }
}