package com.sixbynine.transit.hblr.util

object AndroidPlatformUtils : PlatformUtils {
    override val ioDispatcher = kotlinx.coroutines.Dispatchers.IO
}

actual val platformUtils: PlatformUtils = AndroidPlatformUtils
