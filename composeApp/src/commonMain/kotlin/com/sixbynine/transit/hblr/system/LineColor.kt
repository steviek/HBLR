package com.sixbynine.transit.hblr.system

enum class LineColor {
    Blue,
    Green,
    Yellow;

    companion object {
        val EightStreetHoboken = Blue
        val WestSideTonnelle = Yellow

        fun fromString(value: String): LineColor {
            return when {
                value.first() == 'B' -> Blue
                value.first() == 'G' -> Green
                value.first() == 'Y' -> Yellow
                else -> throw IllegalArgumentException("Invalid line color: $value")
            }
        }
    }
}
