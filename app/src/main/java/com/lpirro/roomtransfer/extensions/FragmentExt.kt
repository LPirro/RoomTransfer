package com.lpirro.roomtransfer.extensions

import android.content.res.Configuration
import androidx.fragment.app.Fragment

fun Fragment.isOrientationPortrait(): Boolean {
    return activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT
}
