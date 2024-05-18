package com.sixbynine.transit.hblr.util

import kotlinx.browser.window

object WasmPlatformUtils : PlatformUtils {
    override val ioDispatcher = kotlinx.coroutines.Dispatchers.Main

    override fun getQueryParams(): Map<String, String> {
        val params = mutableMapOf<String, String>()
        val query = window.location.search.substring(1) // Remove the '?' at the start
        val pairs = query.split("&").map { it.split("=") }

        for (pair in pairs) {
            if (pair.size == 2) {
                params[pair[0]] = pair[1]
            }
        }
        return params
    }
}

actual val platformUtils: PlatformUtils = WasmPlatformUtils
