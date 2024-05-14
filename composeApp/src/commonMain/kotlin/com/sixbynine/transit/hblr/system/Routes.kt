package com.sixbynine.transit.hblr.system

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
import kotlin.time.Duration.Companion.minutes

private val EighthStreetToHoboken = route(Line.EighthStreetHoboken) {
    EighthStreet at 0.minutes
    TwentySecondStreet at 2.minutes
    ThirtyFourthStreet at 4.minutes
    FortyFifthStreet at 6.minutes
    DanforthAvenue at 8.minutes
    RichardStreet at 9.minutes
    LibertyStatePark at 12.minutes
    JerseyAvenue at 15.minutes
    MarinBoulevard at 16.minutes
    EssexStreet at 18.minutes
    ExchangePlace at 20.minutes
    Harborside at 22.minutes
    HarsimusCove at 24.minutes
    Newport at 27.minutes
    Hoboken at 31.minutes
}

private val BayonneFlyerNorth = route(Line.BayonneFlyerNorth) {
    EighthStreet at 0.minutes
    TwentySecondStreet at 2.minutes
    ThirtyFourthStreet at 4.minutes
    FortyFifthStreet at 6.minutes
    EssexStreet at 15.minutes
    ExchangePlace at 17.minutes
    Harborside at 19.minutes
    Newport at 23.minutes
    Hoboken at 27.minutes
}

private val LspToHoboken = route(Line.LspHoboken) {
    LibertyStatePark at 0.minutes
    JerseyAvenue at 3.minutes
    MarinBoulevard at 4.minutes
    EssexStreet at 6.minutes
    ExchangePlace at 8.minutes
    Harborside at 10.minutes
    HarsimusCove at 12.minutes
    Newport at 15.minutes
    Hoboken at 19.minutes
}

private val TwentySecondToHoboken = route(Line.TwentySecondHoboken) {
    TwentySecondStreet at 0.minutes
    ThirtyFourthStreet at 2.minutes
    FortyFifthStreet at 4.minutes
    DanforthAvenue at 6.minutes
    RichardStreet at 7.minutes
    LibertyStatePark at 10.minutes
    JerseyAvenue at 13.minutes
    MarinBoulevard at 14.minutes
    EssexStreet at 16.minutes
    ExchangePlace at 18.minutes
    Harborside at 20.minutes
    HarsimusCove at 22.minutes
    Newport at 25.minutes
    Hoboken at 29.minutes
}

private val DanforthToHoboken = route(Line.DanforthHoboken) {
    DanforthAvenue at 0.minutes
    RichardStreet at 1.minutes
    LibertyStatePark at 4.minutes
    JerseyAvenue at 7.minutes
    MarinBoulevard at 8.minutes
    EssexStreet at 10.minutes
    ExchangePlace at 12.minutes
    Harborside at 14.minutes
    HarsimusCove at 16.minutes
    Newport at 19.minutes
    Hoboken at 23.minutes
}

private val WestSideToTonnelle = route(Line.WestSideTonnelle) {
    WestSideAvenue at 0.minutes
    MLKDrive at 2.minutes
    GarfieldAvenue at 4.minutes
    LibertyStatePark at 7.minutes
    JerseyAvenue at 10.minutes
    MarinBoulevard at 11.minutes
    EssexStreet at 14.minutes
    ExchangePlace at 16.minutes
    Harborside at 18.minutes
    HarsimusCove at 20.minutes
    Newport at 23.minutes
    SecondStreet at 28.minutes
    NinthStreet at 30.minutes
    LincolnHarbor at 33.minutes
    PortImperial at 36.minutes
    BergenlineAvenue at 38.minutes
    TonnelleAvenue at 40.minutes
}

private val LspToTonnelle = route(Line.LspTonnelle) {
    LibertyStatePark at 0.minutes
    JerseyAvenue at 3.minutes
    MarinBoulevard at 4.minutes
    EssexStreet at 7.minutes
    ExchangePlace at 9.minutes
    Harborside at 11.minutes
    HarsimusCove at 13.minutes
    Newport at 16.minutes
    SecondStreet at 21.minutes
    NinthStreet at 23.minutes
    LincolnHarbor at 26.minutes
    PortImperial at 29.minutes
    BergenlineAvenue at 31.minutes
    TonnelleAvenue at 33.minutes
}

private val HobokenToTonnelle = route(Line.HobokenTonnelle) {
    Hoboken at 0.minutes
    SecondStreet at 4.minutes
    NinthStreet at 6.minutes
    LincolnHarbor at 9.minutes
    PortImperial at 12.minutes
    BergenlineAvenue at 14.minutes
    TonnelleAvenue at 16.minutes
}

private val TonnelleToWestSide = route(Line.TonnelleWestSide) {
    TonnelleAvenue at 0.minutes
    BergenlineAvenue at 2.minutes
    PortImperial at 4.minutes
    LincolnHarbor at 7.minutes
    NinthStreet at 10.minutes
    SecondStreet at 11.minutes
    Newport at 17.minutes
    HarsimusCove at 19.minutes
    Harborside at 21.minutes
    ExchangePlace at 23.minutes
    EssexStreet at 25.minutes
    MarinBoulevard at 27.minutes
    JerseyAvenue at 28.minutes
    LibertyStatePark at 31.minutes
    GarfieldAvenue at 34.minutes
    MLKDrive at 36.minutes
    WestSideAvenue at 38.minutes
}

private val HobokenToEighthStreet = route(Line.HobokenEighthStreet) {
    Hoboken at 0.minutes
    Newport at 3.minutes
    HarsimusCove at 5.minutes
    Harborside at 7.minutes
    ExchangePlace at 9.minutes
    EssexStreet at 11.minutes
    MarinBoulevard at 13.minutes
    JerseyAvenue at 14.minutes
    LibertyStatePark at 17.minutes
    RichardStreet at 20.minutes
    DanforthAvenue at 21.minutes
    FortyFifthStreet at 23.minutes
    ThirtyFourthStreet at 25.minutes
    TwentySecondStreet at 27.minutes
    EighthStreet at 30.minutes
}

private val HobokenToTwentySecondStreet = route(Line.HobokenEighthStreet) {
    Hoboken at 0.minutes
    Newport at 3.minutes
    HarsimusCove at 5.minutes
    Harborside at 7.minutes
    ExchangePlace at 9.minutes
    EssexStreet at 11.minutes
    MarinBoulevard at 13.minutes
    JerseyAvenue at 14.minutes
    LibertyStatePark at 17.minutes
    RichardStreet at 20.minutes
    DanforthAvenue at 21.minutes
    FortyFifthStreet at 23.minutes
    ThirtyFourthStreet at 25.minutes
    TwentySecondStreet at 27.minutes
}

private val RichardToEighthStreet = route(Line.RichardEighthStreet) {
    RichardStreet at 0.minutes
    DanforthAvenue at 1.minutes
    FortyFifthStreet at 3.minutes
    ThirtyFourthStreet at 5.minutes
    TwentySecondStreet at 7.minutes
    EighthStreet at 10.minutes
}

private val BayonneFlyerSouth = route(Line.BayonneFlyerSouth) {
    Hoboken at 0.minutes
    Newport at 3.minutes
    Harborside at 7.minutes
    ExchangePlace at 9.minutes
    EssexStreet at 11.minutes
    LibertyStatePark at 16.minutes
    FortyFifthStreet at 21.minutes
    ThirtyFourthStreet at 23.minutes
    TwentySecondStreet at 25.minutes
    EighthStreet at 28.minutes
}

private val HobokenToDanforth = route(Line.HobokenDanforth) {
    Hoboken at 0.minutes
    Newport at 3.minutes
    HarsimusCove at 5.minutes
    Harborside at 7.minutes
    ExchangePlace at 9.minutes
    EssexStreet at 11.minutes
    MarinBoulevard at 13.minutes
    JerseyAvenue at 14.minutes
    LibertyStatePark at 17.minutes
    RichardStreet at 20.minutes
    DanforthAvenue at 21.minutes
}

private val TonnelleToHoboken = route(Line.TonnelleHoboken) {
    TonnelleAvenue at 0.minutes
    BergenlineAvenue at 2.minutes
    PortImperial at 4.minutes
    LincolnHarbor at 7.minutes
    NinthStreet at 10.minutes
    SecondStreet at 11.minutes
    Hoboken at 16.minutes
}

private val GarfieldToWestSide = route(Line.GarfieldWestSide) {
    GarfieldAvenue at 0.minutes
    MLKDrive at 2.minutes
    WestSideAvenue at 4.minutes
}

private val HobokenToWestSide = route(Line.HobokenWestSide) {
    Hoboken at 0.minutes
    Newport at 3.minutes
    HarsimusCove at 5.minutes
    Harborside at 7.minutes
    ExchangePlace at 9.minutes
    EssexStreet at 11.minutes
    MarinBoulevard at 13.minutes
    JerseyAvenue at 14.minutes
    LibertyStatePark at 17.minutes
    GarfieldAvenue at 20.minutes
    MLKDrive at 22.minutes
    WestSideAvenue at 24.minutes
}

private val LspToWestSide = route(Line.LspWestSide) {
    LibertyStatePark at 0.minutes
    GarfieldAvenue at 3.minutes
    MLKDrive at 5.minutes
    WestSideAvenue at 7.minutes
}

val Line.route: Route
    get() = when (this) {
        Line.EighthStreetHoboken -> EighthStreetToHoboken
        Line.DanforthHoboken -> DanforthToHoboken
        Line.TwentySecondHoboken -> TwentySecondToHoboken
        Line.WestSideTonnelle -> WestSideToTonnelle
        Line.LspTonnelle -> LspToTonnelle
        Line.HobokenEighthStreet -> HobokenToEighthStreet
        Line.TonnelleWestSide -> TonnelleToWestSide
        Line.HobokenTonnelle -> HobokenToTonnelle
        Line.TonnelleHoboken -> TonnelleToHoboken
        Line.BayonneFlyerNorth -> BayonneFlyerNorth
        Line.BayonneFlyerSouth -> BayonneFlyerSouth
        Line.LspHoboken -> LspToHoboken
        Line.RichardEighthStreet -> RichardToEighthStreet
        Line.HobokenTwentySecond -> HobokenToTwentySecondStreet
        Line.HobokenDanforth -> HobokenToDanforth
        Line.GarfieldWestSide -> GarfieldToWestSide
        Line.HobokenWestSide -> HobokenToWestSide
        Line.LspWestSide -> LspToWestSide
    }
