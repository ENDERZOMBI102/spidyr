package dev.remod.spidyr

import java.util.*

fun <E> MutableList<E>.immutable(): List<E> {
	return Collections.unmodifiableList( this )
}