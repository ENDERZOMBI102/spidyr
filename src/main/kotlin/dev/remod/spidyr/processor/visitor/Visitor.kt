package dev.remod.spidyr.processor.visitor

import dev.remod.spidyr.types.ast.*

interface Visitor {
	fun visitRoot( tree: AstRoot )
	fun visitImport( imp: String )
	fun visitFunction( tree: AstFunction )
	fun visitClass( tree: AstClass )
	fun visitVariableDecl(tree: AstVariableDeclaration )
	fun visitAssignment( tree: AstAssignment )
	fun visitUnary( tree: AstUnary )
	fun visitLiteral( tree: AstLiteral )
}
