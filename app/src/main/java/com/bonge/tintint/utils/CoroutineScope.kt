package com.bonge.tintint.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun launch(unit: suspend () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        unit.invoke()
    }
}

suspend fun withMain(unit: () -> Unit) {
    withContext(Dispatchers.Main) {
        unit.invoke()
    }
}