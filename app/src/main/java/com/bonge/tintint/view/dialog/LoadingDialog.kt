package com.bonge.tintint.view.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.bonge.tintint.databinding.DialogLoadingBinding
import com.bonge.tintint.utils.LogUtil

class LoadingDialog(context: Context) {
    private val loadingDialog =
        Dialog(context)
    private val binding: DialogLoadingBinding by lazy {
        val layoutInflater = LayoutInflater.from(context)
        DialogLoadingBinding.inflate(layoutInflater)
    }

    init {
        loadingDialog.setContentView(binding.root)
        loadingDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        loadingDialog.setCancelable(false)
    }

    fun show() {
        LogUtil.d("[對話框] 讀取開始")
        loadingDialog.show()
    }

    fun dismiss() {
        LogUtil.d("[對話框] 讀取結束")
        loadingDialog.dismiss()
    }
}