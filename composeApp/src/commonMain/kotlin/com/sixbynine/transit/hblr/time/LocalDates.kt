package com.sixbynine.transit.hblr.time

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

fun LocalDate.Companion.now(): LocalDate {
    return LocalDateTime.now().date
}
