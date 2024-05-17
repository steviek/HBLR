package com.sixbynine.transit.hblr.time

import kotlinx.datetime.LocalTime

object TimeFormatting {
    fun formatTime(time: LocalTime): String {
        return time.hour.toString().padStart(2, '0') + ":" + time.minute.toString().padStart(2, '0')
    }
}