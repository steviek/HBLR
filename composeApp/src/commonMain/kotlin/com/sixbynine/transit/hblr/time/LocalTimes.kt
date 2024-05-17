package com.sixbynine.transit.hblr.time

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

fun LocalTime.Companion.now(): LocalTime {
    return LocalDateTime.now().time
}