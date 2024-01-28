package com.bonge.tintint.utils

import android.content.Context

object Util {

    fun getWindexProportionWidth(context: Context, proportion: Double = 0.7): Int {
        return (context.resources.displayMetrics.widthPixels * proportion).toInt()
    }

}