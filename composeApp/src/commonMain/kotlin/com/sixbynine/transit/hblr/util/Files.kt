package com.sixbynine.transit.hblr.util

import hblr.composeapp.generated.resources.Res
import kotlinx.coroutines.withContext

object Files {
    suspend fun readLines(filename: String): List<String> = withContext(IoDispatcher) {
        Res.readBytes("files/$filename").decodeToString().split('\n')
    }
}

val IoDispatcher = platformUtils.ioDispatcher
