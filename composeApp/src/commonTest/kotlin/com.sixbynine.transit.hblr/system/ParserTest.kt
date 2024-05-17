package com.sixbynine.transit.hblr.system

import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class ParserTest {
    @Test
    fun foo() = runTest {
        val result = Schedules.Weekday.await()
        println(result)
    }
}