package com.sixbynine.transit.hblr.time

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month.MAY
import kotlin.test.Test
import kotlin.test.assertEquals

class BigLocalTimeTest {
    @Test
    fun atTime() {
        val date = LocalDate(2024, MAY, 16)

        assertEquals(
            LocalDateTime(2024, MAY, 16, 0, 0),
            date.atTime(BigLocalTime(0, 0))
        )

        assertEquals(
            LocalDateTime(2024, MAY, 17, 0, 0),
            date.atTime(BigLocalTime(24, 0))
        )

        assertEquals(
            LocalDateTime(2024, MAY, 17, 2, 0),
            date.atTime(BigLocalTime(26, 0))
        )

        assertEquals(
            LocalDateTime(2024, MAY, 16, 7, 0),
            date.atTime(BigLocalTime(7, 0))
        )

        assertEquals(
            LocalDateTime(2024, MAY, 15, 22, 0),
            date.atTime(BigLocalTime(-2, 0))
        )
    }

    @Test
    fun plusMinutes() {
        assertEquals(
            BigLocalTime(0, 0),
            BigLocalTime(0, 0).plusMinutes(0)
        )

        assertEquals(
            BigLocalTime(0, 1),
            BigLocalTime(0, 0).plusMinutes(1)
        )

        assertEquals(
            BigLocalTime(0, 59),
            BigLocalTime(0, 0).plusMinutes(59)
        )

        assertEquals(
            BigLocalTime(1, 0),
            BigLocalTime(0, 0).plusMinutes(60)
        )

        assertEquals(
            BigLocalTime(25, 20),
            BigLocalTime(23, 50).plusMinutes(90)
        )
    }
}