package me.gamercoder215.socketmc.util

// Sizeable

operator fun ElementBounds.unaryPlus(): ElementBounds =
    ElementBounds(+width, +height)
operator fun ElementBounds.unaryMinus(): ElementBounds =
    ElementBounds(-width, -height)
operator fun ElementBounds.inc(): ElementBounds =
    ElementBounds(width + 1, height + 1)
operator fun ElementBounds.dec(): ElementBounds =
    ElementBounds(width - 1, height - 1)

operator fun ElementBounds.plus(other: ElementBounds): ElementBounds =
    ElementBounds(width + other.width, height + other.height)
operator fun ElementBounds.minus(other: ElementBounds): ElementBounds =
    ElementBounds(width - other.width, height - other.height)
operator fun ElementBounds.times(other: ElementBounds): ElementBounds =
    ElementBounds(width * other.width, height * other.height)
operator fun ElementBounds.div(other: ElementBounds): ElementBounds =
    ElementBounds(width / other.width, height / other.height)
operator fun ElementBounds.plus(other: Int): ElementBounds =
    ElementBounds(width + other, height + other)
operator fun ElementBounds.minus(other: Int): ElementBounds =
    ElementBounds(width - other, height - other)
operator fun ElementBounds.times(other: Int): ElementBounds =
    ElementBounds(width * other, height * other)
operator fun ElementBounds.div(other: Int): ElementBounds =
    ElementBounds(width / other, height / other)
operator fun Int.plus(other: ElementBounds): ElementBounds =
    ElementBounds(this + other.width, this + other.height)
operator fun Int.minus(other: ElementBounds): ElementBounds =
    ElementBounds(this - other.width, this - other.height)
operator fun Int.times(other: ElementBounds): ElementBounds =
    ElementBounds(this * other.width, this * other.height)
operator fun Int.div(other: ElementBounds): ElementBounds =
    ElementBounds(this / other.width, this / other.height)

operator fun ElementBounds.component1(): Int = x
operator fun ElementBounds.component2(): Int = y
operator fun ElementBounds.component3(): Int = width
operator fun ElementBounds.component4(): Int = height