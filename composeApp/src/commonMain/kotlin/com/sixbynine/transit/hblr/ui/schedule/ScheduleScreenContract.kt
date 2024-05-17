package com.sixbynine.transit.hblr.ui.schedule

import com.sixbynine.transit.hblr.system.Station
import com.sixbynine.transit.hblr.system.StationDeparture

object ScheduleScreenContract {
    data class State(
        val time: String,
        val searchText: String,
        val departures: Map<Station, List<StationDeparture>>,
        val overrideText: String,
        val displaySearchBar: Boolean,
        val displayTime: Boolean,
    )

    sealed interface Intent {
        data class SearchTextChanged(val text: String) : Intent
    }
}