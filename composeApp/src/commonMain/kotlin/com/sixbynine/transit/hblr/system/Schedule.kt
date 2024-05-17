package com.sixbynine.transit.hblr.system

import com.sixbynine.transit.hblr.time.BigLocalTime

data class Schedule(val trains: List<ScheduledTrain>, val overrideDescription: String = "") {
    operator fun plus(other: Schedule): Schedule {
        return Schedule(trains + other.trains)
    }
}

data class ScheduledTrain(
    val start: Station,
    val end: Station,
    val isExpress: Boolean,
    val startsAt: BigLocalTime,
    val color: LineColor,
)

val ScheduledTrain.isNorthbound: Boolean
    get() = end.northIndex > start.northIndex

val ScheduledTrain.isSouthbound: Boolean
    get() = end.northIndex < start.northIndex
