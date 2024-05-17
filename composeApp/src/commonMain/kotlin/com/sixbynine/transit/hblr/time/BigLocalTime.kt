package com.sixbynine.transit.hblr.time

import kotlinx.datetime.DateTimeUnit.DayBased
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.atTime
import kotlinx.datetime.minus
import kotlinx.datetime.plus

/** Like [kotlinx.datetime.LocalTime], but allowing hour to be outside the 0-23 range. */
data class BigLocalTime(val hour: Int, val minute: Int): Comparable<BigLocalTime> {
    fun plusHours(hours: Int): BigLocalTime {
        return BigLocalTime(hour + hours, minute)
    }

    fun plusMinutes(minutes: Int): BigLocalTime {
        var newHour = hour
        var newMinutes = minute + minutes
        while (newMinutes < 0) {
            newHour -= 1
            newMinutes += 60
        }
        while (newMinutes >= 60) {
            newHour += 1
            newMinutes -= 60
        }
        return BigLocalTime(newHour, newMinutes)
    }

    override fun compareTo(other: BigLocalTime): Int {
        return hour.compareTo(other.hour).takeIf { it != 0 } ?: minute.compareTo(other.minute)
    }
}

fun LocalDate.atTime(time: BigLocalTime): LocalDateTime {
    var date = this
    var hour = time.hour
    while (hour < 0) {
        date = date.minus(1, DayBased(1))
        hour += 24
    }

    while (hour >= 24) {
        date = date.plus(1, DayBased(1))
        hour -= 24
    }
    return date.atTime(hour, time.minute)
}

fun LocalTime.toBigLocalTime(): BigLocalTime {
    return BigLocalTime(hour, minute)
}

val LocalDateTime.bigLocalTime: BigLocalTime
    get() = BigLocalTime(hour, minute)
