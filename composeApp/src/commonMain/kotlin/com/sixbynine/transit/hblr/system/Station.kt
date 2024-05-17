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

enum class Station {
    EighthStreet,
    TwentySecondStreet,
    ThirtyFourthStreet,
    FortyFifthStreet,
    DanforthAvenue,
    RichardStreet,
    WestSideAvenue,
    MLKDrive,
    GarfieldAvenue,
    LibertyStatePark,
    JerseyAvenue,
    MarinBoulevard,
    EssexStreet,
    ExchangePlace,
    Harborside,
    HarsimusCove,
    Newport,
    Hoboken,
    SecondStreet,
    NinthStreet,
    LincolnHarbor,
    PortImperial,
    BergenlineAvenue,
    TonnelleAvenue;

    companion object {
        fun fromCode(code: String): Station {
            return when (code) {
                "08S" -> EighthStreet
                "22S" -> TwentySecondStreet
                "34S" -> ThirtyFourthStreet
                "45S" -> FortyFifthStreet
                "DAN" -> DanforthAvenue
                "RIC" -> RichardStreet
                "WSA" -> WestSideAvenue
                "MLK" -> MLKDrive
                "GAR" -> GarfieldAvenue
                "LSP" -> LibertyStatePark
                "JER" -> JerseyAvenue
                "MAR" -> MarinBoulevard
                "ESS" -> EssexStreet
                "EXP" -> ExchangePlace
                "HRB" -> Harborside
                "HCV" -> HarsimusCove
                "NEW" -> Newport
                "HOB" -> Hoboken
                "02S" -> SecondStreet
                "09S" -> NinthStreet
                "LIN" -> LincolnHarbor
                "POR" -> PortImperial
                "BER" -> BergenlineAvenue
                "TON" -> TonnelleAvenue
                else -> throw IllegalArgumentException("Unknown station code: $code")
            }
        }
    }
}

val Station.code: String
    get() = when (this) {
        EighthStreet -> "08S"
        TwentySecondStreet -> "22S"
        ThirtyFourthStreet -> "34S"
        FortyFifthStreet -> "45S"
        DanforthAvenue -> "DAN"
        RichardStreet -> "RIC"
        WestSideAvenue -> "WSA"
        MLKDrive -> "MLK"
        GarfieldAvenue -> "GAR"
        LibertyStatePark -> "LSP"
        JerseyAvenue -> "JER"
        MarinBoulevard -> "MAR"
        EssexStreet -> "ESS"
        ExchangePlace -> "EXP"
        Harborside -> "HRB"
        HarsimusCove -> "HCV"
        Newport -> "NEW"
        Hoboken -> "HOB"
        SecondStreet -> "02S"
        NinthStreet -> "09S"
        LincolnHarbor -> "LIN"
        PortImperial -> "POR"
        BergenlineAvenue -> "BER"
        TonnelleAvenue -> "TON"
    }

val Station.northIndex: Int
    get() = when (this) {
        EighthStreet -> 0
        TwentySecondStreet -> 1
        ThirtyFourthStreet -> 2
        FortyFifthStreet -> 3
        DanforthAvenue -> 4
        RichardStreet -> 5
        WestSideAvenue -> 6
        MLKDrive -> 7
        GarfieldAvenue -> 8
        LibertyStatePark -> 9
        JerseyAvenue -> 10
        MarinBoulevard -> 11
        EssexStreet -> 12
        ExchangePlace -> 13
        Harborside -> 14
        HarsimusCove -> 15
        Newport -> 16
        Hoboken -> 17
        SecondStreet -> 18
        NinthStreet -> 19
        LincolnHarbor -> 20
        PortImperial -> 21
        BergenlineAvenue -> 22
        TonnelleAvenue -> 23
    }

val Station.displayName: String
    get() = when (this) {
        EighthStreet -> "8th St (Bayonne)"
        TwentySecondStreet -> "22nd Street (Bayonne)"
        ThirtyFourthStreet -> "34th Street (Bayonne)"
        FortyFifthStreet -> "45th St (Bayonne)"
        DanforthAvenue -> "Danforth Avenue"
        RichardStreet -> "Richard Street"
        WestSideAvenue -> "West Side Avenue"
        MLKDrive -> "MLK Drive"
        GarfieldAvenue -> "Garfield Avenue"
        LibertyStatePark -> "Liberty State Park"
        JerseyAvenue -> "Jersey Avenue"
        MarinBoulevard -> "Marin Boulevard"
        EssexStreet -> "Essex Street"
        ExchangePlace -> "Exchange Place"
        Harborside -> "Harborside"
        HarsimusCove -> "Harsimus Cove"
        Newport -> "Pavonia / Newport"
        Hoboken -> "Hoboken"
        SecondStreet -> "2nd Street (Hoboken)"
        NinthStreet -> "9th Street (Hoboken)"
        LincolnHarbor -> "Lincoln Harbor"
        PortImperial -> "Port Imperial"
        BergenlineAvenue -> "Bergenline Avenue"
        TonnelleAvenue -> "Tonnelle Avenue"
    }
