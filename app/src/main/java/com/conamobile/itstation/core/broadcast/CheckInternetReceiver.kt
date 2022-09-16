package com.conamobile.itstation.core.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.conamobile.itstation.core.utils.NetworkHelper

class CheckInternetReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (!hasInternet(context)) {

        }
    }

    private fun hasInternet(context: Context): Boolean {
        val networkHelper = NetworkHelper(context)
        return networkHelper.isNetworkConnected()
    }
}