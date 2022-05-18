package dev.remod.spidyr.types

data class Token(
    val type: TokenType,
    val symbol: Symbol? = null,
    val value: String = "",
    val file: String,
    val line: Int,
    val col: Int
) {
    override fun toString(): String {
        return when ( type ) {
            TokenType.Symbol -> "Symbol: $symbol"
            TokenType.Newline -> "Newline"
            else -> "$type: $value"
        }
    }
}
