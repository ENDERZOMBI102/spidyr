package dev.remod.spidyr.types.ast

data class AstClass(
    val name: String,
    val extend: String,
    val implement: Array<String>,
    val variables: Array<AstVariable>,
    val functions: Array<AstFunction>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AstClass

        if (name != other.name) return false
        if (extend != other.extend) return false
        if (!implement.contentEquals(other.implement)) return false
        if (!variables.contentEquals(other.variables)) return false
        if (!functions.contentEquals(other.functions)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + extend.hashCode()
        result = 31 * result + implement.contentHashCode()
        result = 31 * result + variables.contentHashCode()
        result = 31 * result + functions.contentHashCode()
        return result
    }
}