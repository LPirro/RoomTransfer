package com.lpirro.roomtransfer.presentation

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lpirro.roomtransfer.R
import com.lpirro.roomtransfer.extensions.getColorFromAttr
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            window.navigationBarColor =
                getColorFromAttr(com.google.android.material.R.attr.backgroundColor)
        }
    }
}
