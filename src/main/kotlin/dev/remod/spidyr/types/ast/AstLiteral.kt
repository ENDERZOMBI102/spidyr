package dev.remod.spidyr.types.ast

import dev.remod.spidyr.processor.visitor.Visitor

class AstLiteral( val type: LiteralType, val value: Any? ) : AstExpression() {

	override fun visit( visitor: Visitor ) {
		visitor.visitLiteral( this )
	}

	companion object {
		val EMPTY: AstLiteral = AstLiteral( LiteralType.Null, null ) // represents an empty literal
		val NULL: AstLiteral = AstLiteral( LiteralType.Null, null )  // represents the null value
		val TRUE: AstLiteral = AstLiteral( LiteralType.Boolean, true )  // represents the true boolean value
		val FALSE: AstLiteral = AstLiteral( LiteralType.Boolean, false )// represents the false boolean value

		fun forString( type: LiteralType, value: String ): AstLiteral {
			return when ( type ) {
				LiteralType.Boolean -> AstLiteral( type, value.toBoolean() )
				LiteralType.Null -> AstLiteral( type, null )
				LiteralType.String -> AstLiteral( type, value )
				LiteralType.Number -> AstLiteral( type, value.toDouble() )
			}
		}
	}

	enum class LiteralType {
		Boolean,
		Null,
		String,
		Number
	}
}