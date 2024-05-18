package com.sixbynine.transit.hblr.util

import kotlinx.coroutines.CoroutineDispatcher

interface PlatformUtils {
    val ioDispatcher: CoroutineDispatcher

    fun getQueryParams(): Map<String, String> = emptyMap()
}

expect val platformUtils: PlatformUtils
