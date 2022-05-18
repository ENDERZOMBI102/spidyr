package dev.remod.spidyr.types.ast

import dev.remod.spidyr.processor.visitor.Visitor

data class AstClass(
	val name: String,
	val extend: String,
	val implement: List<String>,
	val variables: List<AstVariableDeclaration>,
	val functions: List<AstFunction>
) : AstMember {
    override fun equals( other: Any? ): Boolean {
        if ( this === other ) return true
        if ( javaClass != other?.javaClass ) return false

        other as AstClass

        if ( name != other.name ) return false
        if ( extend != other.extend ) return false
        if ( implement != other.implement ) return false
        if ( variables != other.variables ) return false
        if ( functions != other.functions ) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + extend.hashCode()
        result = 31 * result + implement.hashCode()
        result = 31 * result + variables.hashCode()
        result = 31 * result + functions.hashCode()
        return result
    }

    override fun visit( visitor: Visitor ) {
        visitor.visitClass( this )
    }
}
