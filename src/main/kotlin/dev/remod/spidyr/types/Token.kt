package dev.remod.spidyr.types

data class Token(
    val type: String,
    val value: String = ""
) {
    override fun toString(): String {
        return if (value.isEmpty()) type else "$type: $value"
    }
}