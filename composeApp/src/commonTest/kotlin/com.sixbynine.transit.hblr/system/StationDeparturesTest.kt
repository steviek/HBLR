package com.sixbynine.transit.hblr.system

import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import kotlin.test.Test

class StationDeparturesTest {
    @Test
    fun computeDepartures() = runTest {
        val schedule = Schedules.Weekday.await()
        val date = LocalDate(2024, 5, 16)
        val allDepartures = schedule.trains.map { StationDepartures.computeDepartures(it, date) }

        allDepartures.forEach {
            println(it.first())
            it.drop(1).forEach {
                println("\t" + it.station.name + " " + it.dateTime.time.toString())
            }
        }
    }
}