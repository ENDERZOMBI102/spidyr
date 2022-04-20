package dev.remod.spidyr.types.ast

data class AstFunction(
    val name: String,
    val type: String,
    val arguments: Array<AstVariable>,
    val bytecode: Unit
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AstFunction

        if (name != other.name) return false
        if (type != other.type) return false
        if (!arguments.contentEquals(other.arguments)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + arguments.contentHashCode()
        return result
    }
}