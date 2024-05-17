package com.sixbynine.transit.hblr.time

import kotlinx.datetime.LocalTime

object TimeFormatting {
    fun formatTime(time: LocalTime, includeSeconds: Boolean = false): String {
        return time.hour.toString().padStart(2, '0') + ":" +
                time.minute.toString().padStart(2, '0') +
                if (includeSeconds) {
                    ":" + time.second.toString().padStart(2, '0')
                } else {
                    ""
                }
    }
}