package com.sixbynine.transit.hblr.system

import com.sixbynine.transit.hblr.system.Station.Harborside
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month.MAY
import kotlinx.datetime.atTime
import kotlin.test.Test

class DepartureManagerTest {
    @Test
    fun listDepartures() = runTest {
        val departures = DepartureManager.listDepartures(
            Harborside,
            LocalDate(2024, MAY, 16).atTime(12, 0)
        )
        println(departures)
    }
}