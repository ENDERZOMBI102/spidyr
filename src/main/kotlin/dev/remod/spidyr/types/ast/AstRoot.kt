package dev.remod.spidyr.types.ast

import dev.remod.spidyr.processor.visitor.Visitor

data class AstRoot(
	val namespace: String,
	val imports: List<String>,
	val clazz: AstClass
) : AstMember {
    override fun equals(other: Any?): Boolean {
        if ( this === other ) return true
        if ( javaClass != other?.javaClass ) return false

        other as AstRoot

        if ( namespace != other.namespace ) return false
        if ( imports !=other.imports ) return false
        if ( clazz != other.clazz ) return false

        return true
    }

    override fun hashCode(): Int {
        var result = namespace.hashCode()
        result = 31 * result + imports.hashCode()
        result = 31 * result + clazz.hashCode()
        return result
    }

    override fun visit( visitor: Visitor ) {
        visitor.visitRoot( this )
    }
}