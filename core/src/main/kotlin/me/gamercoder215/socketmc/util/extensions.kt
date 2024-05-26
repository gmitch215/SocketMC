package me.gamercoder215.socketmc.util

// Sizeable

operator fun Sizeable.unaryPlus(): Sizeable = Sizeable(+width, +height)
operator fun Sizeable.unaryMinus(): Sizeable = Sizeable(-width, -height)
operator fun Sizeable.inc(): Sizeable = Sizeable(width + 1, height + 1)
operator fun Sizeable.dec(): Sizeable = Sizeable(width - 1, height - 1)

operator fun Sizeable.plus(other: Sizeable): Sizeable = Sizeable(width + other.width, height + other.height)
operator fun Sizeable.minus(other: Sizeable): Sizeable = Sizeable(width - other.width, height - other.height)
operator fun Sizeable.times(other: Sizeable): Sizeable = Sizeable(width * other.width, height * other.height)
operator fun Sizeable.div(other: Sizeable): Sizeable = Sizeable(width / other.width, height / other.height)
operator fun Sizeable.plus(other: Int): Sizeable = Sizeable(width + other, height + other)
operator fun Sizeable.minus(other: Int): Sizeable = Sizeable(width - other, height - other)
operator fun Sizeable.times(other: Int): Sizeable = Sizeable(width * other, height * other)
operator fun Sizeable.div(other: Int): Sizeable = Sizeable(width / other, height / other)
operator fun Int.plus(other: Sizeable): Sizeable = Sizeable(this + other.width, this + other.height)
operator fun Int.minus(other: Sizeable): Sizeable = Sizeable(this - other.width, this - other.height)
operator fun Int.times(other: Sizeable): Sizeable = Sizeable(this * other.width, this * other.height)
operator fun Int.div(other: Sizeable): Sizeable = Sizeable(this / other.width, this / other.height)

operator fun Sizeable.component1(): Int = x
operator fun Sizeable.component2(): Int = y
operator fun Sizeable.component3(): Int = width
operator fun Sizeable.component4(): Int = height