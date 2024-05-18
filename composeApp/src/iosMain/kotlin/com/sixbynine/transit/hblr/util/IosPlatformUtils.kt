package com.sixbynine.transit.hblr.util

object IosPlatformUtils : PlatformUtils {
    override val ioDispatcher = kotlinx.coroutines.Dispatchers.Main
}

actual val platformUtils: PlatformUtils = IosPlatformUtils
