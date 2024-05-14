package com.sixbynine.transit.hblr.system

import kotlin.time.Duration

data class Route(
    val line: Line,
    val stops: List<RouteStop>,
)

data class RouteStop(val station: Station, val time: Duration)

class RouteBuilder(private val line: Line) {
    private val stops = mutableListOf<RouteStop>()

    infix fun Station.at(time: Duration) {
        stops.add(RouteStop(this, time))
    }

    fun build(): Route {
        return Route(line, stops)
    }
}

inline fun route(line: Line, block: RouteBuilder.() -> Unit): Route {
    return RouteBuilder(line).apply(block).build()
}