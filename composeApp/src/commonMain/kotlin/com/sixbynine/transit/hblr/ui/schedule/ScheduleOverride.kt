package com.sixbynine.transit.hblr.ui.schedule

import com.sixbynine.transit.hblr.system.LineColor
import com.sixbynine.transit.hblr.system.Schedule
import com.sixbynine.transit.hblr.system.ScheduledTrain
import com.sixbynine.transit.hblr.system.Station
import com.sixbynine.transit.hblr.time.bigLocalTime
import com.sixbynine.transit.hblr.time.now
import com.sixbynine.transit.hblr.time.plusHours
import com.sixbynine.transit.hblr.time.toBigLocalTime
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class ScheduleOverride(
    val start: LocalDateTime,
    val end: LocalDateTime,
    val trains: List<ScheduledTrain>,
    val description: String,
) {
    companion object {
        private fun create(
            description: String,
            start: LocalDateTime,
            end: LocalDateTime,
            vararg specs: MinutesAfterTheHourSpec
        ): ScheduleOverride {
            var time = start
            val trains = mutableListOf<ScheduledTrain>()
            while (time < end) {
                for (spec in specs) {
                    for (minute in spec.minutes) {
                        trains += ScheduledTrain(
                            start = spec.from,
                            end = spec.to,
                            isExpress = spec.isExpress,
                            startsAt = time.time.toBigLocalTime().plusMinutes(minute),
                            color = spec.color
                        )
                    }
                }
                time = time.plusHours(1)
            }
            return ScheduleOverride(start, end, trains, description)
        }

        fun get(date: LocalDate = LocalDate.now()): ScheduleOverride? {
            if (date == LocalDate(2024, 5, 18)) {
                return May18
            }

            return null
        }

        private val May18 = create(
            description = "May 18 service changes",
            start = LocalDateTime(2021, 5, 18, 6, 0),
            end = LocalDateTime(2021, 5, 18, 18, 0),
            MinutesAfterTheHourSpec(
                from = Station.EighthStreet,
                to = Station.Hoboken,
                minutes = listOf(23, 53),
                color = LineColor.EightStreetHoboken,
            ),
            MinutesAfterTheHourSpec(
                from = Station.WestSideAvenue,
                to = Station.TonnelleAvenue,
                minutes = listOf(25, 55),
                color = LineColor.WestSideTonnelle,
            ),
            MinutesAfterTheHourSpec(
                from = Station.TonnelleAvenue,
                to = Station.WestSideAvenue,
                minutes = listOf(23, 53),
                color = LineColor.EightStreetHoboken,
            ),
            MinutesAfterTheHourSpec(
                from = Station.Hoboken,
                to = Station.EighthStreet,
                minutes = listOf(5, 35),
                color = LineColor.EightStreetHoboken,
            ),
        )
    }
}

private data class MinutesAfterTheHourSpec(
    val from: Station,
    val to: Station,
    val minutes: List<Int>,
    val color: LineColor,
    val isExpress: Boolean = false,
)

fun Schedule.withOverride(override: ScheduleOverride?): Schedule {
    override ?: return this
    val origTrains = trains
        .filter {
            it.startsAt < override.start.bigLocalTime || it.startsAt >= override.end.bigLocalTime
        }
    return Schedule(origTrains + override.trains, override.description)
}

