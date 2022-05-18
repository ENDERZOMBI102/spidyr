package dev.remod.spidyr.types.ast

import dev.remod.spidyr.processor.visitor.Visitor
import dev.remod.spidyr.types.Symbol

class AstUnary(
	left: AstExpression,
	op: Symbol,
	right: AstExpression
) : AstExpression() {
	override fun visit( visitor: Visitor ) {
		visitor.visitUnary( this )
	}
}