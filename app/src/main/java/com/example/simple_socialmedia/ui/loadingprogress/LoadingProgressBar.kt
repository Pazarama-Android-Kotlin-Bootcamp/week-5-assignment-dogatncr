package com.example.simple_socialmedia.ui.loadingprogress

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.simple_socialmedia.R

/**
 ** */

class LoadingProgressBar(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_loading_progress)
        window?.setBackgroundDrawableResource(R.color.transparent)
        setCancelable(false)
    }
}