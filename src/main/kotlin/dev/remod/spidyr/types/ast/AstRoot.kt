package dev.remod.spidyr.types.ast

data class AstRoot(
    val name: String,
    val namespace: String,
    val imports: String,
    val clazz: AstClass
)