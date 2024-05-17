package com.sixbynine.transit.hblr.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.reflect.KClass

class ViewModelStoreOwner {

    private val keyToViewModel = mutableMapOf<String, ViewModel>()


    @Suppress("UNCHECKED_CAST")
    fun <VM : ViewModel> get(key: String, factory: () -> VM): VM {
        return keyToViewModel.getOrPut(key) { factory() } as VM
    }

    fun clear() {
        keyToViewModel.keys.forEach { key ->
            keyToViewModel.remove(key)?.onCleared()
        }
    }
}

@Composable
fun ProvideViewModelStoreOwner(content: @Composable () -> Unit) {
    val viewModelStoreOwner = remember { ViewModelStoreOwner() }
    CompositionLocalProvider(LocalViewModelStoreOwner provides viewModelStoreOwner) {
        content()
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModelStoreOwner.clear()
        }
    }
}

@Composable
inline fun <VM : ViewModel> getViewModel(
    vmClass: KClass<VM>,
    key: String = "",
    noinline factory: () -> VM
): VM {
    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    val fullKey = vmClass.simpleName + key
    return viewModelStoreOwner.get(fullKey, factory)
}

val LocalViewModelStoreOwner: ProvidableCompositionLocal<ViewModelStoreOwner> =
    staticCompositionLocalOf { error("no view model store owner") }

abstract class ViewModel {
    protected val viewModelScope = CoroutineScope(Dispatchers.Main + Job())

    fun onCleared() {
        viewModelScope.cancel()
    }
}