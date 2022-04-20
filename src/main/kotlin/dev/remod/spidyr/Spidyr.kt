package dev.remod.spidyr

import dev.remod.spidyr.processor.AstGenerator
import dev.remod.spidyr.processor.StringParser
import dev.remod.spidyr.processor.Tokenizer
import java.io.File

fun main() {
    val reader = File("./test/main.dyr").bufferedReader()
    val input = reader.use { it.readText() }
    println(input)
    println()
    val parsedString = StringParser.parse(input)
    parsedString.forEach {
        println(it)
    }
    println()
    val tokens = Tokenizer.tokenize(parsedString)
    tokens.forEach {
        println(it)
    }
    println()
    val ast = AstGenerator.generateAst(tokens)
}