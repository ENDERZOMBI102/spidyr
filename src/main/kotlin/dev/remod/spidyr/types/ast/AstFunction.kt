package dev.remod.spidyr.types.ast

import dev.remod.spidyr.processor.visitor.Visitor
import dev.remod.spidyr.types.Token

data class AstFunction(
    val name: String,
    val type: String,
    val arguments: List<AstVariableDeclaration>,
    val body: List<Token>
) : AstMember {
    override fun equals(other: Any?): Boolean {
        if ( this === other )
            return true
        if ( javaClass != other?.javaClass )
            return false

        other as AstFunction

        if ( name != other.name || type != other.type || arguments != other.arguments )
            return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + arguments.hashCode()
        return result
    }

    override fun visit( visitor: Visitor ) {
        visitor.visitFunction( this )
    }
}