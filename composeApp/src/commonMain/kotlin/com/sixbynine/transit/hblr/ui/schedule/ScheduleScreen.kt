package com.sixbynine.transit.hblr.ui.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sixbynine.transit.hblr.system.LineColor
import com.sixbynine.transit.hblr.system.LineColor.Blue
import com.sixbynine.transit.hblr.system.LineColor.Green
import com.sixbynine.transit.hblr.system.LineColor.Yellow
import com.sixbynine.transit.hblr.system.StationDeparture
import com.sixbynine.transit.hblr.system.displayName
import com.sixbynine.transit.hblr.system.headSign
import com.sixbynine.transit.hblr.system.isNorthbound
import com.sixbynine.transit.hblr.time.TimeFormatting
import com.sixbynine.transit.hblr.ui.getViewModel
import com.sixbynine.transit.hblr.ui.schedule.ScheduleScreenContract.Intent.SearchTextChanged

@Composable
fun ScheduleScreen() {
    val viewModel = getViewModel(ScheduleViewModel::class, "schedule") { ScheduleViewModel() }
    val state by viewModel.state.collectAsState()
    val onIntent = viewModel::onIntent

    Scaffold { padding ->
        Column(
            Modifier.padding(padding).padding(horizontal = 64.dp, vertical = 16.dp).fillMaxWidth()
        ) {
            Box(
                Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            ) {
                OutlinedTextField(
                    modifier = Modifier.align(Alignment.TopCenter),
                    value = state.searchText,
                    onValueChange = { onIntent(SearchTextChanged(it)) },
                    label = { Text("Search") },
                )

                if (state.overrideText.isNotBlank()) {
                    Text(
                        text = "Including " + state.overrideText,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            }


            LazyColumn(contentPadding = PaddingValues(top = 16.dp)) {
                state.departures.forEach { (station, departures) ->
                    item {
                        Text(
                            modifier = Modifier.padding(vertical = 8.dp),
                            text = station.displayName,
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }

                    val (northbound, southbound) = departures.partition { it.isNorthbound }

                    item {
                        Row {
                            Column(Modifier.weight(1f)) {
                                if (northbound.isNotEmpty()) {
                                    Text(
                                        modifier = Modifier.padding(vertical = 8.dp),
                                        text = "Northbound",
                                        style = MaterialTheme.typography.headlineSmall
                                    )
                                }

                                northbound.forEach { departure ->
                                    DepartureLine(departure)
                                }
                            }

                            Spacer(Modifier.width(32.dp))

                            Column(Modifier.weight(1f)) {
                                if (southbound.isNotEmpty()) {
                                    Text(
                                        modifier = Modifier.padding(vertical = 8.dp),
                                        text = "Southbound",
                                        style = MaterialTheme.typography.headlineSmall
                                    )
                                }

                                southbound.forEach { departure ->
                                    DepartureLine(departure)
                                }
                            }
                        }

                        Spacer(Modifier.height(32.dp))
                    }
                }
            }

        }
    }
}

@Composable
private fun DepartureLine(departure: StationDeparture) {
    Row(
        Modifier.padding(vertical = 8.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ColorCircle(departure.lineColor)
        Spacer(Modifier.width(16.dp))
        Text(departure.headSign, style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.weight(1f))
        Spacer(Modifier.width(16.dp))
        Text(
            TimeFormatting.formatTime(departure.dateTime.time),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun ColorCircle(lineColor: LineColor) {
    val color = when (lineColor) {
        Blue -> Color(red = 5, green = 166, blue = 228)
        Green -> Color(red = 0, green = 143, blue = 99)
        Yellow -> Color(red = 254, green = 206, blue = 5)
    }
    Box(Modifier.clip(CircleShape).size(36.dp).background(color))
}

