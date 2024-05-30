package me.gamercoder215.socketmc.util

// Sizeable

operator fun Position.unaryPlus(): Position =
    Position(+width, +height)
operator fun Position.unaryMinus(): Position =
    Position(-width, -height)
operator fun Position.inc(): Position =
    Position(width + 1, height + 1)
operator fun Position.dec(): Position =
    Position(width - 1, height - 1)

operator fun Position.plus(other: Position): Position =
    Position(width + other.width, height + other.height)
operator fun Position.minus(other: Position): Position =
    Position(width - other.width, height - other.height)
operator fun Position.times(other: Position): Position =
    Position(width * other.width, height * other.height)
operator fun Position.div(other: Position): Position =
    Position(width / other.width, height / other.height)
operator fun Position.plus(other: Int): Position =
    Position(width + other, height + other)
operator fun Position.minus(other: Int): Position =
    Position(width - other, height - other)
operator fun Position.times(other: Int): Position =
    Position(width * other, height * other)
operator fun Position.div(other: Int): Position =
    Position(width / other, height / other)
operator fun Int.plus(other: Position): Position =
    Position(this + other.width, this + other.height)
operator fun Int.minus(other: Position): Position =
    Position(this - other.width, this - other.height)
operator fun Int.times(other: Position): Position =
    Position(this * other.width, this * other.height)
operator fun Int.div(other: Position): Position =
    Position(this / other.width, this / other.height)

operator fun Position.component1(): Int = x
operator fun Position.component2(): Int = y
operator fun Position.component3(): Int = width
operator fun Position.component4(): Int = height