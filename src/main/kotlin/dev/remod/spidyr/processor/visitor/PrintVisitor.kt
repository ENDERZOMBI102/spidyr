package dev.remod.spidyr.processor.visitor

import dev.remod.spidyr.types.ast.*

class PrintVisitor : Visitor {
	private var indentLevel = 0

	override fun visitRoot( tree: AstRoot ) {
		println( "Root: ${tree.namespace}" )
		for ( imp in tree.imports )
			visitImport( imp )
		visitClass( tree.clazz )
	}

	override fun visitImport( imp: String ) {
		println( "Import: $imp" )
	}

	override fun visitFunction( tree: AstFunction ) {
		indented( "Function: ${tree.name} -> ${tree.type}" )
		indentLevel++
		for ( def in tree.arguments )
			visitVariableDecl( def )
		indentLevel--
	}

	override fun visitClass( tree: AstClass ) {
		println( "Class: ${tree.name} <- ${tree.extend}, ${tree.implement}" )
		indentLevel++
		for ( def in tree.variables )
			visitVariableDecl( def )
		for ( def in tree.functions )
			visitFunction( def )
		indentLevel--
	}

	override fun visitVariableDecl(tree: AstVariableDeclaration ) {
		indented(
			"Variable: ${tree.name} of type ${tree.type}" +
			if ( tree.defaultValueSource != null ) " ( '${tree.defaultValueSource}' by default )" else ""
		)
	}

	override fun visitAssignment(tree: AstAssignment) {
		TODO("Not yet implemented")
	}

	override fun visitUnary(tree: AstUnary) {
		TODO("Not yet implemented")
	}

	override fun visitLiteral(tree: AstLiteral ) {
		TODO("Not yet implemented")
	}

	private fun indented( msg: String ) {
		println( "${"\t".repeat( indentLevel )}- $msg" )
	}

}
