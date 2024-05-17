package com.sixbynine.transit.hblr.ui.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.platform.LocalDensity
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

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        BoxWithConstraints(Modifier.padding(padding).fillMaxSize()) {
            val density = LocalDensity.current
            val maxWidthDp = with(density) { constraints.maxWidth.toDp() }
            val isWideScreen = maxWidthDp >= 600.dp
            Column(
                Modifier
                    .padding(horizontal = if (isWideScreen) 64.dp else 16.dp)
                    .fillMaxSize()
            ) {
                if (isWideScreen) {
                    Box(
                        Modifier.fillMaxWidth(),
                    ) {
                        if (state.displaySearchBar) {
                            OutlinedTextField(
                                modifier = Modifier.align(Alignment.TopCenter).padding(bottom = 16.dp),
                                value = state.searchText,
                                onValueChange = { onIntent(SearchTextChanged(it)) },
                                label = { Text("Search") },
                            )
                        }

                        Row(
                            Modifier.align(Alignment.CenterEnd),
                        ) {
                            if (state.overrideText.isNotBlank()) {
                                Text(
                                    text = "Including " + state.overrideText,
                                )
                            }
                        }
                    }
                } else {
                    Column {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth()
                                .padding(vertical = 8.dp),
                            value = state.searchText,
                            onValueChange = { onIntent(SearchTextChanged(it)) },
                            label = { Text("Search") },
                        )

                        if (state.overrideText.isNotBlank()) {
                            Text(
                                text = "Including " + state.overrideText,
                            )
                        }
                    }
                }

                LazyColumn(contentPadding = PaddingValues(vertical = 16.dp)) {
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
                            Column {
                                if (isWideScreen) {
                                    Row {
                                        DepartureSection(
                                            "Northbound",
                                            northbound,
                                            Modifier.weight(1f)
                                        )

                                        Spacer(Modifier.width(64.dp))

                                        DepartureSection(
                                            "Southbound",
                                            southbound,
                                            Modifier.weight(1f)
                                        )
                                    }
                                } else {
                                    DepartureSection(
                                        "Northbound",
                                        northbound,
                                        Modifier.fillMaxWidth()
                                    )

                                    Spacer(Modifier.height(32.dp))

                                    DepartureSection(
                                        "Southbound",
                                        southbound,
                                        Modifier.fillMaxWidth()
                                    )
                                }
                            }

                            Spacer(Modifier.height(32.dp))
                        }
                    }
                }

            }

            if (state.displayTime) {
                Box(
                    Modifier.align(Alignment.TopEnd)
                        .padding(horizontal = if (isWideScreen) 64.dp else 16.dp, vertical = 16.dp)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Text(
                        text = state.time,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

            }
        }
    }
}

@Composable
private fun DepartureSection(
    title: String,
    departures: List<StationDeparture>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        if (departures.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = title,
                style = MaterialTheme.typography.headlineSmall
            )
        }

        departures.forEach { departure ->
            DepartureLine(departure)
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

