package dev.remod.spidyr.types.ast

data class AstRoot(
    val namespace: String,
    val imports: Array<String>,
    val clazz: AstClass? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AstRoot

        if (namespace != other.namespace) return false
        if (!imports.contentEquals(other.imports)) return false
        if (clazz != other.clazz) return false

        return true
    }

    override fun hashCode(): Int {
        var result = namespace.hashCode()
        result = 31 * result + imports.contentHashCode()
        result = 31 * result + (clazz?.hashCode() ?: 0)
        return result
    }
}