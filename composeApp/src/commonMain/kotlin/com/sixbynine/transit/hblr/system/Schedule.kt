package com.sixbynine.transit.hblr.system

import com.sixbynine.transit.hblr.system.Line.BayonneFlyerNorth
import com.sixbynine.transit.hblr.system.Line.BayonneFlyerSouth
import com.sixbynine.transit.hblr.system.Line.DanforthHoboken
import com.sixbynine.transit.hblr.system.Line.EighthStreetHoboken
import com.sixbynine.transit.hblr.system.Line.GarfieldWestSide
import com.sixbynine.transit.hblr.system.Line.HobokenDanforth
import com.sixbynine.transit.hblr.system.Line.HobokenEighthStreet
import com.sixbynine.transit.hblr.system.Line.HobokenTonnelle
import com.sixbynine.transit.hblr.system.Line.HobokenTwentySecond
import com.sixbynine.transit.hblr.system.Line.HobokenWestSide
import com.sixbynine.transit.hblr.system.Line.LspHoboken
import com.sixbynine.transit.hblr.system.Line.LspTonnelle
import com.sixbynine.transit.hblr.system.Line.LspWestSide
import com.sixbynine.transit.hblr.system.Line.RichardEighthStreet
import com.sixbynine.transit.hblr.system.Line.TonnelleHoboken
import com.sixbynine.transit.hblr.system.Line.TonnelleWestSide
import com.sixbynine.transit.hblr.system.Line.TwentySecondHoboken
import com.sixbynine.transit.hblr.system.Line.WestSideTonnelle
import com.sixbynine.transit.hblr.time.LocalTime

data class Schedule(val departures: List<Departure>) {
    operator fun plus(other: Schedule): Schedule {
        return Schedule(departures + other.departures)
    }
}

class Departure(val route: Line, val time: LocalTime)

private class ScheduleBuilder {
    private val departures = mutableListOf<Departure>()

    infix fun Int.h(other: Int) : LocalTime {
        return LocalTime(this, other)
    }

    infix fun LocalTime.on(line: Line) {
        departures.add(Departure(line, this))
    }

    infix fun List<LocalTime>.on(line: Line) {
        forEach {
            departures.add(Departure(line, it))
        }
    }

    fun Line.at(vararg time: LocalTime) {
        time.forEach {
            departures.add(Departure(this, it))
        }
    }

    fun build(): List<Departure> {
        return departures
    }
}

private inline fun schedule(block: ScheduleBuilder.() -> Unit): List<Departure> {
    return ScheduleBuilder().apply(block).build()
}

val Sunday = schedule {
    // Blue line north
    listOf(5 h 20, 5 h 41, 6 h 1) on LspHoboken
    (6..23).forEach { hour ->
        listOf(hour h 1, hour h 29, hour h 49) on EighthStreetHoboken
    }
    listOf(24 h 20, 24 h 54, 25 h 24) on EighthStreetHoboken

    // Yellow line north
    5 h 35 on LspTonnelle
    5 h 48 on WestSideTonnelle
    (6..23).forEach { hour ->
        listOf(hour h 8, hour h 28, hour h 48) on WestSideTonnelle
    }
    listOf(24 h 18, 24 h 48) on WestSideTonnelle

    // Yellow line south
    listOf(5 h 36, 5 h 56) on TonnelleWestSide
    (6..23).forEach { hour ->
        listOf(hour h 16, hour h 36, hour h 56) on TonnelleWestSide
    }
    listOf(24 h 16, 24 h 36, 25 h 1, 25 h 31) on TonnelleWestSide

    // Blue line south
    5 h 45 on HobokenEighthStreet
    (6..23).forEach { hour ->
        listOf(hour h 5, hour h 25, hour h 45) on HobokenEighthStreet
    }
    listOf(24 h 5, 24 h 35, 25 h 5, 25 h 35, 26 h 5) on HobokenEighthStreet
}

val Saturday = Sunday + schedule {
    // Only on Saturday?
    6 h 10 on GarfieldWestSide
}

val Weekday = schedule {
    // Blue line north - weird one-offs
    listOf(4 h 37, 4 h 42, 4 h 57, 5 h 8) on LspHoboken
    listOf(7 h 3, 8 h 2, 8 h 46, 9 h 2, 10 h 5) on TwentySecondHoboken
    listOf(16 h 33, 16 h 41, 16 h 58, 17 h 12, 18 h 0) on TwentySecondHoboken
    listOf(7 h 38, 8 h 28, 17 h 38) on DanforthHoboken

    // Blue line north - main local route
    listOf(5 h 11, 5 h 25, 5 h 40, 5 h 54, 6 h 6, 6 h 18, 6 h 36, 6 h 47) on EighthStreetHoboken
    listOf(7 h 16, 7 h 45, 8 h 10, 8 h 29, 9 h 11, 9 h 23, 9 h 32, 9 h 49) on EighthStreetHoboken
    (10..14).forEach { hour ->
        listOf(hour h 11, hour h 31, hour h 51) on EighthStreetHoboken
    }
    listOf(15 h 9, 15 h 17, 15 h 29, 15 h 35, 15 h 48, 15 h 54, 16 h 7) on EighthStreetHoboken
    listOf(16 h 26, 16 h 47, 17 h 8, 17 h 22, 17 h 35, 17 h 39, 17 h 55) on EighthStreetHoboken
    listOf(18 h 16, 18 h 29, 18 h 51, 19 h 1, 19 h 17, 19 h 39) on EighthStreetHoboken
    (20..24).forEach { hour ->
        listOf(hour h 9, hour h 39) on EighthStreetHoboken
    }
    25 h 9 on EighthStreetHoboken

    // Blue line north - "express"
    listOf(6 h 33, 6 h 58, 7 h 12, 7 h 28, 7 h 39, 7 h 51, 7 h 58, 8 h 19) on BayonneFlyerNorth
    listOf(8 h 40, 8 h 54) on BayonneFlyerNorth
    listOf(16 h 23, 16 h 53, 18 h 8, 18 h 43) on BayonneFlyerNorth

    // Yellow line north
    listOf(5 h 9, 5 h 21, 5 h 36, 5 h 50, 6 h 4, 6 h 16, 6 h 26, 6 h 38, 6 h 47) on WestSideTonnelle
    listOf(6 h 57, 7 h 9, 7 h 18, 7 h 27, 7 h 38, 7 h 45, 7 h 57, 8 h 8) on WestSideTonnelle
    listOf(8 h 18, 8 h 31, 8 h 39, 8 h 45, 9 h 2, 9 h 14, 9 h 24, 9 h 35) on WestSideTonnelle
    9 h 49 on WestSideTonnelle
    (10..14).forEach {
        listOf(it h 6, it h 26, it h 46) on WestSideTonnelle
    }
    listOf(15 h 5, 15 h 18, 15 h 27, 15 h 37, 15 h 49, 15 h 55, 16 h 10) on WestSideTonnelle
    listOf(16 h 20, 16 h 28, 16 h 41, 16 h 41, 16 h 49, 16 h 59, 17 h 9) on WestSideTonnelle
    listOf(17 h 19, 17 h 25, 17 h 38, 17 h 47, 17 h 54, 18 h 6, 18 h 17) on WestSideTonnelle
    listOf(18 h 28, 18 h 40, 18 h 51, 19 h 2, 19 h 16, 19 h 26, 19 h 56) on WestSideTonnelle
    (20..24).forEach {
        listOf(it h 26, it h 56) on WestSideTonnelle
    }

    // Green line north
    listOf(5 h 9, 5 h 23, 5 h 37, 5 h 50, 6 h 3, 6 h 18, 6 h 32, 6 h 44) on HobokenTonnelle
    listOf(6 h 58, 7 h 14, 7 h 27, 7 h 43, 7 h 56, 8 h 13, 8 h 26, 8 h 43) on HobokenTonnelle
    listOf(8 h 58, 9 h 12, 9 h 23, 9 h 40, 9 h 52) on HobokenTonnelle
    (10..14).forEach {
        listOf(it h 4, it h 24, it h 44) on HobokenTonnelle
    }
    listOf(15 h 4, 15 h 19, 15 h 32, 15 h 46, 16 h 4, 16 h 14, 16 h 29) on HobokenTonnelle
    listOf(16 h 45, 17 h 1, 17 h 14, 17 h 27, 17 h 44, 17 h 59, 18 h 15) on HobokenTonnelle
    listOf(18 h 32, 18 h 45, 18 h 58, 19 h 12, 19 h 37) on HobokenTonnelle
    (20..25).forEach {
        listOf(it h 7, it h 37) on HobokenTonnelle
    }

    // Blue line south - weird one-offs
    listOf(5 h 8, 5 h 36, 7 h 34) on RichardEighthStreet
    listOf(7 h 12, 7 h 59, 17 h 10) on HobokenDanforth
    listOf(7 h 29, 8 h 11, 8 h 29, 8 h 56, 9 h 4, 9 h 34, 9 h 43) on HobokenTwentySecond
    listOf(16 h 2, 16 h 23, 16 h 41, 17 h 29, 17 h 50) on HobokenTwentySecond

    // Blue line south
    listOf(5 h 6, 5 h 21, 5 h 34, 5 h 46, 6 h 1, 6 h 15, 6 h 41, 6 h 53) on HobokenEighthStreet
    listOf(7 h 3, 7 h 36, 7 h 45, 7 h 54, 8 h 37, 8 h 39, 8 h 50, 9 h 10) on HobokenEighthStreet
    listOf(9 h 18, 9 h 29, 9 h 50, 10 h 0, 10 h 10, 10 h 31, 10 h 40) on HobokenEighthStreet
    10 h 55 on HobokenEighthStreet
    (11..14).forEach {
        listOf(it h 15, it h 35, it h 55) on HobokenEighthStreet
    }
    listOf(15 h 13, 15 h 30, 15 h 44, 15 h 52, 16 h 16, 16 h 47, 16 h 59) on HobokenEighthStreet
    listOf(17 h 16, 17 h 36, 17 h 57, 18 h 7, 18 h 19, 18 h 42, 18 h 53) on HobokenEighthStreet
    listOf(19 h 1, 19 h 16, 19 h 27, 19 h 35, 19 h 45) on HobokenEighthStreet
    (20..25).forEach {
        listOf(it h 5, it h 35) on HobokenEighthStreet
    }
    26 h 5 on HobokenEighthStreet

    // Blue line south - "express"
    listOf(7 h 8, 7 h 22, 8 h 7, 8 h 22) on BayonneFlyerSouth
    listOf(16 h 10, 16 h 36, 17 h 6, 17 h 24, 17 h 46, 18 h 12, 18 h 30) on BayonneFlyerSouth
    19 h 7 on BayonneFlyerSouth

    // Yellow line south
    4 h 54 on LspWestSide
    5 h 11 on GarfieldWestSide
    5 h 0 on HobokenWestSide
    listOf(5 h 5, 5 h 19, 5 h 36, 5 h 50, 6 h 4, 6 h 16, 6 h 26, 6 h 36, 6 h 48) on TonnelleWestSide
    listOf(7 h 2, 7 h 12, 7 h 25, 7 h 37, 7 h 44, 7 h 56, 8 h 2, 8 h 14, 8 h 22) on TonnelleWestSide
    listOf(8 h 30, 8 h 41, 8 h 53, 9 h 1, 9 h 14, 9 h 24, 9 h 34, 9 h 54) on TonnelleWestSide
    (10..14).forEach {
        listOf(it h 14, it h 34, it h 54) on TonnelleWestSide
    }
    listOf(15 h 14, 15 h 29, 15 h 37, 15 h 46, 15 h 54, 16 h 5, 16 h 12) on TonnelleWestSide
    listOf(16 h 20, 16 h 32, 16 h 42, 16 h 55, 17 h 5, 17 h 13, 17 h 25) on TonnelleWestSide
    listOf(17 h 35, 17 h 46, 17 h 56, 18 h 2, 18 h 10, 18 h 21, 18 h 31) on TonnelleWestSide
    listOf(18 h 38, 18 h 50, 19 h 0, 19 h 11, 19 h 24, 19 h 46, 20 h 0) on TonnelleWestSide
    (20..25).forEach {
        listOf(it h 10, it h 40) on TonnelleWestSide
    }

    // Green line south
    listOf(4 h 44, 4 h 59, 5 h 14, 5 h 29, 5 h 43, 5 h 57, 6 h 11, 6 h 23) on TonnelleHoboken
    listOf(6 h 53, 7 h 4, 7 h 34, 7 h 52, 8 h 4, 8 h 20, 8 h 33, 8 h 47) on TonnelleHoboken
    listOf(9 h 3, 9 h 19, 9 h 32, 9 h 43, 10 h 2, 10 h 24, 10 h 44) on TonnelleHoboken
    (11..14).forEach {
        listOf(it h 4, it h 24, it h 44) on TonnelleHoboken
    }
    listOf(14 h 58, 15 h 12, 15 h 26, 15 h 41, 15 h 52, 16 h 7, 16 h 24) on TonnelleHoboken
    listOf(16 h 36, 16 h 49, 17 h 7, 17 h 21, 17 h 39, 17 h 52, 18 h 8) on TonnelleHoboken
    listOf(18 h 25, 18 h 36, 18 h 52, 19 h 8, 19 h 34, 19 h 58) on TonnelleHoboken
    (20..24).forEach {
        listOf(it h 28, it h 58) on TonnelleHoboken
    }
}