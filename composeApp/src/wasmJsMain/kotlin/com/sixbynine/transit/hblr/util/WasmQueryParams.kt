package com.sixbynine.transit.hblr.util

import kotlinx.browser.window

actual fun getQueryParams(): Map<String, String> {
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
