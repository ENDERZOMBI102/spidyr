package dev.remod.spidyr.processor

import dev.remod.spidyr.exceptions.NoSemicolonException
import dev.remod.spidyr.immutable
import dev.remod.spidyr.types.Symbol
import dev.remod.spidyr.types.Token
import dev.remod.spidyr.types.TokenType
import dev.remod.spidyr.types.ast.*

// TODO: Add errors where it makes sense to
class AstGenerator( private val tokens: List<Token> ) {
    private var index: Int = 0

    fun generateAst(): AstRoot {
        var pkg = ""

        if ( consumeIfEqual( value="ns" ) ) {
            pkg = parsePath()
        }

        checkNewline()

        val imports = ArrayList<String>()
        while ( consumeIfEqual( value="in" ) ) {
            imports += parsePath()
            checkNewline()
        }
        val className = consume().value
        var extends = ""
        val impls = mutableListOf<String>()
        val vars = mutableListOf<AstVariableDeclaration>()
        val funcs = mutableListOf<AstFunction>()
        // is it extending something?
        if ( consumeIfEqual( symbol = Symbol.COLON ) ) {
            // it is
            // first is extends
            if ( peek().type == TokenType.Name )
                extends = consume().value
            // second+ is implements
            while ( consumeIfEqual( symbol = Symbol.COMMA ) ) {
                impls += consume().value
            }
        }
        println( tokens.subList( index, tokens.size ) )
        consume() // B_OPEN
        // variables
        variables( vars )
        consumeIfEqual( type = TokenType.Newline )
        // functions
        while ( peek(1).symbol == Symbol.P_OPEN ) {
            val name = consume().value
            consume() // (
            val args = variables( mutableListOf() )
            consume() // )

            val body = mutableListOf<Token>()
            var layer = 0

            // TODO: analyze returns
           do {
                if ( peek().symbol == Symbol.B_CLOSE )
                    layer--
                else if ( peek().symbol == Symbol.B_OPEN )
                    layer++
                body += consume()
            }  while ( layer != 0 )

            funcs += AstFunction(
                name,
                "void",
                args.immutable(),
                body.immutable()
            )
        }

        return AstRoot(
            pkg,
            imports.immutable(),
            AstClass(
                className,
                extends,
                impls.immutable(),
                vars.immutable(),
                funcs.immutable()
            )
        )
    }

    private fun peek( offset: Int = 0 ): Token {
        return if ( tokens.size > index + offset )
            tokens[index + offset]
        else
            Token(
                TokenType.End,
                file=tokens[0].file,
                line=tokens[ tokens.lastIndex ].line,
                col=tokens[ tokens.lastIndex ].col + tokens[ tokens.lastIndex ].value.length
            )
    }

    private fun consume(): Token {
        return tokens[index++]
    }

    private fun consumeIfEqual(
        offset: Int = 0,
        value: String? = null,
        symbol: Symbol = Symbol.NOT_SYMBOL,
        type: TokenType? = null
    ): Boolean {
        if ( peek( offset ).value == value || peek( offset ).symbol == symbol || peek( offset ).type == type ) {
            consume()
            return true
        }
        return false
    }

    private fun checkNewline() {
        if ( consume().type != TokenType.Newline ) {
            val tkn = peek()
            throw NoSemicolonException( tkn.file, tkn.line, tkn.col )
        }
    }

    private fun variables( vars: MutableList<AstVariableDeclaration> ): MutableList<AstVariableDeclaration> {
        while (
            peek(1).symbol != Symbol.P_OPEN &&
            peek(0).symbol != Symbol.P_CLOSE &&
            peek(0).type != TokenType.Newline
        ) {
            val name = consume().value
            var type = ""
            var defaultValueSource: AstExpression = AstLiteral.EMPTY
            // default value?
            // NAME [ = DEF_VALUE ] [ : TYPE ]
            //  - type without value: use default value
            //  - value without type: detect type
            if ( consumeIfEqual( symbol = Symbol.EQUAL ) ) {
                // has def value
                val tkn = consume()
                defaultValueSource = AstLiteral.forString(
                    AstLiteral.LiteralType.valueOf( tkn.type.name ),
                    tkn.value
                )
            }
            if ( consumeIfEqual( symbol = Symbol.COLON ) ) {
                // has type
                type = consume().value
            }
            vars += AstVariableDeclaration( name, type, defaultValueSource )
            consumeIfEqual( symbol = Symbol.COMMA )
        }
        return vars
    }

    private fun parsePath(): String {
        var str = ""

        while ( peek().type == TokenType.Name || peek().symbol == Symbol.DOT ) {
            str += if ( peek().type == TokenType.Name ) consume().value else {
                consume()
                "."
            }
        }

        return str
    }
}