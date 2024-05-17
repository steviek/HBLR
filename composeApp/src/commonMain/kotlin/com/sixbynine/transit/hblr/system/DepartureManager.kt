package com.sixbynine.transit.hblr.system

import com.sixbynine.transit.hblr.time.plusHours
import com.sixbynine.transit.hblr.ui.schedule.ScheduleOverride
import com.sixbynine.transit.hblr.ui.schedule.withOverride
import kotlinx.datetime.DayOfWeek.SATURDAY
import kotlinx.datetime.DayOfWeek.SUNDAY
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

object DepartureManager {
    suspend fun listDepartures(station: Station, dateTime: LocalDateTime): List<StationDeparture> {
        val schedule = getSchedule(dateTime.date)
        return schedule
            .trains
            .mapNotNull { train ->
                StationDepartures.computeDeparture(train, station, dateTime.date)
            }
            .filter { it.dateTime >= dateTime && it.dateTime <= dateTime.plusHours(1) }
            .sortedBy { it.dateTime }
    }

    suspend fun allDepartures(dateTime: LocalDateTime): Map<Station, List<StationDeparture>> {
        val schedule = getSchedule(dateTime.date)
        return schedule
            .trains
            .flatMap { train ->
                StationDepartures.computeDepartures(train, dateTime.date)
            }
            .filter { it.dateTime >= dateTime && it.dateTime <= dateTime.plusHours(1) }
            .groupBy { it.station }
            .mapValues { (_, value) -> value.sortedBy { departure -> departure.dateTime } }
    }

    // TODO: Handle holidays
    suspend fun getSchedule(date: LocalDate): Schedule {
        val override = ScheduleOverride.get(date)

        val schedule = when (date.dayOfWeek) {
            SATURDAY -> Schedules.Saturday
            SUNDAY -> Schedules.Sunday
            else -> Schedules.Weekday
        }
        return schedule.await().withOverride(override)
    }
}
