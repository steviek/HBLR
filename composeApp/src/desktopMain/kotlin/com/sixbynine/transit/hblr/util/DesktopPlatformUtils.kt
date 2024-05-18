package com.sixbynine.transit.hblr.util

object DesktopPlatformUtils : PlatformUtils {
    override val ioDispatcher = kotlinx.coroutines.Dispatchers.IO
}

actual val platformUtils: PlatformUtils = DesktopPlatformUtils
