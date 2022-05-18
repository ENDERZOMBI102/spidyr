package dev.remod.spidyr.exceptions

class NoSemicolonException( val file: String, val line: Int, val col: Int ) : Exception( "$file:$line:$col" )