package dev.remod.spidyr.types.ast

import dev.remod.spidyr.processor.visitor.Visitor

data class AstVariableDeclaration(
    val name: String,
    val type: String,
    val defaultValueSource: AstExpression?
) : AstMember {
    override fun visit( visitor: Visitor ) {
        visitor.visitVariableDecl( this )
    }
}