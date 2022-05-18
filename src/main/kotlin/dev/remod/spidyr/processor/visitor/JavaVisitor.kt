package dev.remod.spidyr.processor.visitor

import dev.remod.spidyr.types.ast.*
import dev.remod.spidyr.types.ast.AstLiteral.LiteralType

class JavaVisitor : Visitor {
	private var indentLevel = 0

	override fun visitRoot( tree: AstRoot ) {
		println( "package ${tree.namespace};\n" )

		for ( imp in tree.imports )
			visitImport( imp )

		if ( tree.imports.isNotEmpty() )
			println("\n")

		visitClass( tree.clazz )
	}

	override fun visitImport( imp: String ) {
		println( "import $imp;" )
	}

	override fun visitFunction( tree: AstFunction ) {
		indented( "public ${tree.type} ${tree.name}(" )
		for ( def in tree.arguments ) {
			if ( def != tree.arguments[0] )
				print(",")
			print(" ")
			visitVariableDecl(def)
			print(" ")
		}
		print(") { }")
	}

	override fun visitClass( tree: AstClass ) {
		println(
			"public class ${tree.name} " +
			( if ( tree.extend.isEmpty() ) "" else "extends ${tree.extend} " ) +
			( if ( tree.implement.isEmpty() ) "" else "implements ${tree.implement.joinToString(", ")} " ) +
			"{"
		)
		indentLevel++
		for ( def in tree.variables ) {
			indented( "public " )
			visitVariableDecl( def )
			print(";\n")
		}
		if ( tree.functions.isNotEmpty() )
			println()
		for ( def in tree.functions ) {
			visitFunction(def)
			print("\n")
		}
		indentLevel--
		println("}")
	}

	override fun visitVariableDecl(tree: AstVariableDeclaration ) {
		print( "${tree.type} ${tree.name}" )
		if ( tree.defaultValueSource != AstLiteral.EMPTY ) {
			print(" = ")
			if ( AstLiteral::class.isInstance( tree.defaultValueSource ) ) {

				val source = tree.defaultValueSource as AstLiteral

				when ( source.type ) {
					LiteralType.Boolean -> print( source.value as Boolean )
					LiteralType.Null -> print( null )
					LiteralType.String -> print( source.value as String )
					LiteralType.Number -> print( source.value as Number )
				}

			} else
				print( tree.defaultValueSource )
		}
	}

	override fun visitAssignment( tree: AstAssignment ) {
		TODO("Not yet implemented")
	}

	override fun visitUnary( tree: AstUnary ) {
		TODO("Not yet implemented")
	}

	override fun visitLiteral( tree: AstLiteral ) {
		TODO("Not yet implemented")
	}

	private fun indented( line: String, end: String = "" ) {
		print( "${"\t".repeat( indentLevel )}$line$end" )
	}

}
