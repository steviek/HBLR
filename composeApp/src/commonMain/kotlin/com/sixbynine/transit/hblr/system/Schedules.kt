package com.sixbynine.transit.hblr.system

import com.sixbynine.transit.hblr.DefaultScope
import com.sixbynine.transit.hblr.time.BigLocalTime
import com.sixbynine.transit.hblr.util.Files
import kotlinx.coroutines.CoroutineStart.LAZY
import kotlinx.coroutines.async

object Schedules {
    val Weekday = DefaultScope.async(start = LAZY) {
        parseSchedule("weekday")
    }

    val Saturday = DefaultScope.async(start = LAZY) {
        parseSchedule("saturday")
    }

    val Sunday = DefaultScope.async(start = LAZY) {
        parseSchedule("sunday")
    }

    private suspend fun parseSchedule(name: String): Schedule {
        val lines = Files.readLines("schedule_$name.csv")
        val departures = lines.subList(1, lines.size - 1).map { line ->
            val parts = line.split(',')
            val hour = parts[0].toInt()
            val minute = parts[1].toInt()
            val isExpress = parts[2] == "1"
            val start = Station.fromCode(parts[3])
            val end = Station.fromCode(parts[4])
            val color = LineColor.fromString(parts[5])
            ScheduledTrain(
                start = start,
                end = end,
                startsAt = BigLocalTime(hour, minute),
                color = color,
                isExpress = isExpress
            )
        }
        return Schedule(departures)
    }
}