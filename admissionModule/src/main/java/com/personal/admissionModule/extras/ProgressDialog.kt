package com.getfly.technologies.extras

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ProgressBar

class ProgressDialog(context: Context) : Dialog(context)  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ProgressBar(context))
        setCancelable(false) // Make it non-cancelable
    }
}