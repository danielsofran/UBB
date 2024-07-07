package com.example.mobileandroid.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class MyWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context.applicationContext, params) {

    override suspend fun doWork(): Result {
        // get network status
        val networkStatus = isOnline(applicationContext)
        showToast("Network status: $networkStatus")
        val result = workDataOf("networkStatus" to networkStatus)
        return Result.success(result)
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                showToast("TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                showToast("TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                showToast("TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }


    private fun showToast(message: String) {
        applicationContext.let {
            android.os.Handler(it.mainLooper).post {
                val toast = Toast.makeText(it, message, Toast.LENGTH_SHORT)
                val handler = android.os.Handler()
                handler.postDelayed({ toast.cancel() }, 1000)
                toast.show()
            }
        }
    }
}