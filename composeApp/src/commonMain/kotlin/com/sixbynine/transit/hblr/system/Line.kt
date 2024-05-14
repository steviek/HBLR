package com.sixbynine.transit.hblr.system

import com.sixbynine.transit.hblr.system.Line.BayonneFlyerNorth
import com.sixbynine.transit.hblr.system.Line.BayonneFlyerSouth
import com.sixbynine.transit.hblr.system.Line.EighthStreetHoboken
import com.sixbynine.transit.hblr.system.Line.DanforthHoboken
import com.sixbynine.transit.hblr.system.Line.GarfieldWestSide
import com.sixbynine.transit.hblr.system.Line.HobokenEighthStreet
import com.sixbynine.transit.hblr.system.Line.HobokenDanforth
import com.sixbynine.transit.hblr.system.Line.HobokenTonnelle
import com.sixbynine.transit.hblr.system.Line.HobokenTwentySecond
import com.sixbynine.transit.hblr.system.Line.LspHoboken
import com.sixbynine.transit.hblr.system.Line.LspTonnelle
import com.sixbynine.transit.hblr.system.Line.RichardEighthStreet
import com.sixbynine.transit.hblr.system.Line.TonnelleHoboken
import com.sixbynine.transit.hblr.system.Line.TonnelleWestSide
import com.sixbynine.transit.hblr.system.Line.TwentySecondHoboken
import com.sixbynine.transit.hblr.system.Line.WestSideTonnelle

enum class LineColors {
    Blue,
    Green,
    Yellow
}

enum class Line {
    EighthStreetHoboken,
    HobokenEighthStreet,
    WestSideTonnelle,
    TonnelleWestSide,
    HobokenTonnelle,
    TonnelleHoboken,
    BayonneFlyerNorth,
    BayonneFlyerSouth,
    LspHoboken,
    RichardEighthStreet,
    TwentySecondHoboken,
    HobokenTwentySecond,
    DanforthHoboken,
    HobokenDanforth,
    LspTonnelle,
    GarfieldWestSide,
}

val Line.colors: LineColors
    get() = when (this) {
        EighthStreetHoboken, HobokenEighthStreet, BayonneFlyerNorth, BayonneFlyerSouth,
        LspHoboken, TwentySecondHoboken, HobokenTwentySecond,
        DanforthHoboken, HobokenDanforth, RichardEighthStreet -> LineColors.Blue

        WestSideTonnelle, TonnelleWestSide, LspTonnelle, GarfieldWestSide -> LineColors.Yellow

        HobokenTonnelle, TonnelleHoboken -> LineColors.Green
    }