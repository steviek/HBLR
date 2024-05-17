package com.sixbynine.transit.hblr.time

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun LocalDateTime.Companion.now(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}

fun LocalDateTime.plusHours(hours: Int): LocalDateTime {
    return date.atTime(time.toBigLocalTime().plusHours(hours))
}
