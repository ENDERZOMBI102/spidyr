package dev.remod.spidyr.types.ast

import dev.remod.spidyr.processor.visitor.Visitor

interface AstMember {
	fun visit( visitor: Visitor )
}
