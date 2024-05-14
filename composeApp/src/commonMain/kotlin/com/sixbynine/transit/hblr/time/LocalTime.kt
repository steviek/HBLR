package com.sixbynine.transit.hblr.time

// Note: Can't use kotlin-date-time for wasm yet
data class LocalTime(val hour: Int, val minute: Int)