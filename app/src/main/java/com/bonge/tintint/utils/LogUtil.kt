package com.bonge.tintint.utils

import android.util.Log
import com.bonge.tintint.BuildConfig

object LogUtil {
    fun d(message: String) {
        Log.d(BuildConfig.APPLICATION_ID, message)
    }

    fun e(message: String?, tr: Throwable?) {
        Log.e(BuildConfig.APPLICATION_ID, message, tr)
    }
}