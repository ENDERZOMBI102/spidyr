package dev.remod.spidyr.types.ast

import dev.remod.spidyr.processor.visitor.Visitor

class AstAssignment(
	destination: String,
	source: AstExpression
) : AstMember {
	override fun visit( visitor: Visitor ) {
		visitor.visitAssignment( this )
	}
}