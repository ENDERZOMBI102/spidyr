package dev.remod.spidyr

import dev.remod.spidyr.processor.AstGenerator
import dev.remod.spidyr.processor.visitor.PrintVisitor
import dev.remod.spidyr.processor.StringParser
import dev.remod.spidyr.processor.Tokenizer
import dev.remod.spidyr.processor.visitor.JavaVisitor
import java.io.File

fun main() {
    val reader = File("./test/visitor.dyr").bufferedReader()
    val input = reader.use { it.readText() }
    println(input)
//    println()
    val parsedString = StringParser.parse(input)
//    parsedString.forEach { println(it) }
//    println()
    val tokens = Tokenizer.tokenize( "./test/visitor.dyr", parsedString )
//    tokens.forEach { println(it) }

    println("\n${"-".repeat(40)}\n")
    val ast = AstGenerator( tokens ).generateAst()
    ast.visit( PrintVisitor() )
    println("\n${"-".repeat(40)}\n")
    ast.visit( JavaVisitor() )
}