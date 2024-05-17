package com.sixbynine.transit.hblr.ui.schedule

import com.sixbynine.transit.hblr.system.DepartureManager
import com.sixbynine.transit.hblr.system.Station
import com.sixbynine.transit.hblr.system.StationDeparture
import com.sixbynine.transit.hblr.system.displayName
import com.sixbynine.transit.hblr.time.TimeFormatting
import com.sixbynine.transit.hblr.time.now
import com.sixbynine.transit.hblr.ui.ViewModel
import com.sixbynine.transit.hblr.ui.schedule.ScheduleScreenContract.State
import com.sixbynine.transit.hblr.util.getQueryParams
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlin.time.Duration.Companion.seconds

class ScheduleViewModel : ViewModel() {

    private var allDepartures: Map<Station, List<StationDeparture>> = emptyMap()

    private val _state = MutableStateFlow(createInitialState())
    val state = _state

    init {
        viewModelScope.launch {
            while (true) {
                allDepartures = DepartureManager.allDepartures(LocalDateTime.now())
                updateState {
                    copy(
                        title = "Departures at ${TimeFormatting.formatTime(LocalTime.now())}",
                        departures = getDeparturesForSearchText(searchText)
                    )
                }
                delay(30.seconds)
            }
        }
    }

    fun onIntent(intent: ScheduleScreenContract.Intent) {
        when (intent) {
            is ScheduleScreenContract.Intent.SearchTextChanged -> {
                updateState {
                    copy(
                        searchText = intent.text,
                        departures = getDeparturesForSearchText(intent.text)
                    )
                }

            }
        }
    }

    private fun getDeparturesForSearchText(text: String): Map<Station, List<StationDeparture>> {
        if (text.isBlank()) return allDepartures
        return allDepartures
            .filterKeys {
                it.displayName.contains(
                    text,
                    ignoreCase = true
                )
            }
    }

    private fun createInitialState(): State {
        val queryParams = getQueryParams()
        return State(
            title = "Departures at ${TimeFormatting.formatTime(LocalTime.now())}",
            searchText = queryParams["q"].orEmpty(),
            departures = emptyMap(),
            overrideText = ScheduleOverride.get()?.description ?: ""
        )
    }

    private fun updateState(block: State.() -> State) {
        _state.value = _state.value.block()
    }
}