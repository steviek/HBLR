package com.sixbynine.transit.hblr

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

val MainScope by lazy { CoroutineScope(Dispatchers.Main) }
val DefaultScope by lazy { CoroutineScope(Dispatchers.Default) }