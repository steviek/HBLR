package com.sixbynine.transit.hblr.system

import com.sixbynine.transit.hblr.DefaultScope
import com.sixbynine.transit.hblr.system.LineColor.Blue
import com.sixbynine.transit.hblr.system.LineColor.Green
import com.sixbynine.transit.hblr.system.LineColor.Yellow
import com.sixbynine.transit.hblr.system.Station.BergenlineAvenue
import com.sixbynine.transit.hblr.system.Station.DanforthAvenue
import com.sixbynine.transit.hblr.system.Station.EighthStreet
import com.sixbynine.transit.hblr.system.Station.EssexStreet
import com.sixbynine.transit.hblr.system.Station.ExchangePlace
import com.sixbynine.transit.hblr.system.Station.FortyFifthStreet
import com.sixbynine.transit.hblr.system.Station.GarfieldAvenue
import com.sixbynine.transit.hblr.system.Station.Harborside
import com.sixbynine.transit.hblr.system.Station.HarsimusCove
import com.sixbynine.transit.hblr.system.Station.Hoboken
import com.sixbynine.transit.hblr.system.Station.JerseyAvenue
import com.sixbynine.transit.hblr.system.Station.LibertyStatePark
import com.sixbynine.transit.hblr.system.Station.LincolnHarbor
import com.sixbynine.transit.hblr.system.Station.MLKDrive
import com.sixbynine.transit.hblr.system.Station.MarinBoulevard
import com.sixbynine.transit.hblr.system.Station.Newport
import com.sixbynine.transit.hblr.system.Station.NinthStreet
import com.sixbynine.transit.hblr.system.Station.PortImperial
import com.sixbynine.transit.hblr.system.Station.RichardStreet
import com.sixbynine.transit.hblr.system.Station.SecondStreet
import com.sixbynine.transit.hblr.system.Station.ThirtyFourthStreet
import com.sixbynine.transit.hblr.system.Station.TonnelleAvenue
import com.sixbynine.transit.hblr.system.Station.TwentySecondStreet
import com.sixbynine.transit.hblr.system.Station.WestSideAvenue
import com.sixbynine.transit.hblr.time.atTime
import com.sixbynine.transit.hblr.util.Files
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

object StationDepartures {
    val Legs = DefaultScope.async(start = CoroutineStart.LAZY) {
        val lines = Files.readLines("legs.csv")
        lines.subList(1, lines.size - 1).associate { line ->
            val parts = line.split(',')
            val from = Station.fromCode(parts[0])
            val to = Station.fromCode(parts[1])
            val minutes = parts[2].toInt()
            Pair(from, to) to minutes
        }
    }

    suspend fun computeDepartures(train: ScheduledTrain, date: LocalDate): List<StationDeparture> {
        val stationDepartures = mutableListOf<StationDeparture>()

        forEachStop(train, date) { station, time ->
            if (station == train.end) return@forEachStop
            stationDepartures += StationDeparture(
                station = station,
                destination = train.end,
                isExpress = train.isExpress,
                lineColor = train.color,
                dateTime = time,
            )
        }

        return stationDepartures
    }

    suspend fun computeDeparture(
        train: ScheduledTrain,
        station: Station,
        date: LocalDate
    ): StationDeparture? {
        forEachStop(train, date) { curStation, time ->
            if (curStation == station) {
                return StationDeparture(
                    station = curStation,
                    destination = train.end,
                    isExpress = train.isExpress,
                    lineColor = train.color,
                    dateTime = time,
                )
            }
        }

        return null
    }

    private suspend inline fun forEachStop(
        train: ScheduledTrain,
        date: LocalDate,
        block: (Station, LocalDateTime) -> Unit
    ): StationDeparture? {
        val legs = Legs.await()

        var curStation: Station? = train.start
        var time = train.startsAt
        while (curStation != null) {
            block(curStation, date.atTime(time))

            val nextStation = curStation.nextStop(train)
            if (nextStation != null) {
                val leg =
                    legs[Pair(curStation, nextStation)]
                        ?: error("No leg found from $nextStation to $nextStation")
                time = time.plusMinutes(leg)
            }

            curStation = nextStation
        }

        return null
    }

    private fun Station.nextStop(train: ScheduledTrain): Station? {
        if (this == train.end) return null

        return when (this) {
            EighthStreet -> when {
                train.isNorthbound -> TwentySecondStreet
                else -> null
            }

            TwentySecondStreet -> when {
                train.isNorthbound -> ThirtyFourthStreet
                else -> EighthStreet
            }

            ThirtyFourthStreet -> when {
                train.isNorthbound -> FortyFifthStreet
                else -> TwentySecondStreet
            }

            FortyFifthStreet -> when {
                train.isSouthbound -> ThirtyFourthStreet
                train.isExpress -> EssexStreet
                else -> DanforthAvenue
            }

            DanforthAvenue -> when {
                train.isNorthbound -> RichardStreet
                else -> FortyFifthStreet
            }

            RichardStreet -> when {
                train.isNorthbound -> LibertyStatePark
                else -> DanforthAvenue
            }

            WestSideAvenue -> when {
                train.isNorthbound -> MLKDrive
                else -> null
            }

            MLKDrive -> when {
                train.isNorthbound -> GarfieldAvenue
                else -> WestSideAvenue
            }

            GarfieldAvenue -> when {
                train.isNorthbound -> LibertyStatePark
                else -> MLKDrive
            }

            LibertyStatePark -> when {
                train.isNorthbound -> JerseyAvenue
                train.color == Yellow -> GarfieldAvenue
                train.isExpress -> FortyFifthStreet
                else -> RichardStreet
            }

            JerseyAvenue -> when {
                train.isNorthbound -> MarinBoulevard
                else -> LibertyStatePark
            }

            MarinBoulevard -> when {
                train.isNorthbound -> EssexStreet
                else -> JerseyAvenue
            }

            EssexStreet -> when {
                train.isNorthbound -> ExchangePlace
                train.isExpress -> LibertyStatePark
                else -> MarinBoulevard
            }

            ExchangePlace -> when {
                train.isNorthbound -> Harborside
                else -> EssexStreet
            }

            Harborside -> when {
                train.isSouthbound -> ExchangePlace
                train.isExpress -> Newport
                else -> HarsimusCove
            }

            HarsimusCove -> when {
                train.isNorthbound -> Newport
                else -> Harborside
            }

            Newport -> when {
                train.isNorthbound && train.color == Blue -> Hoboken
                train.isNorthbound -> SecondStreet
                train.isExpress -> Harborside
                else -> HarsimusCove
            }

            Hoboken -> when {
                train.isNorthbound -> SecondStreet
                else -> Newport
            }

            SecondStreet -> when {
                train.isNorthbound -> NinthStreet
                train.color == Green -> Hoboken
                else -> Newport
            }

            NinthStreet -> when {
                train.isNorthbound -> LincolnHarbor
                else -> SecondStreet
            }

            LincolnHarbor -> when {
                train.isNorthbound -> PortImperial
                else -> NinthStreet
            }

            PortImperial -> when {
                train.isNorthbound -> BergenlineAvenue
                else -> LincolnHarbor
            }

            BergenlineAvenue -> when {
                train.isNorthbound -> TonnelleAvenue
                else -> PortImperial
            }

            TonnelleAvenue -> when {
                train.isNorthbound -> null
                else -> BergenlineAvenue
            }
        }
    }
}

data class StationDeparture(
    val station: Station,
    val destination: Station,
    val isExpress: Boolean,
    val lineColor: LineColor,
    val dateTime: LocalDateTime,
)

val StationDeparture.headSign: String
    get() {
        if (!isExpress) {
            return destination.displayName
        }

        return destination.displayName + " (Bayonne Flyer)"
    }

val StationDeparture.isNorthbound: Boolean
    get() = destination.northIndex > station.northIndex