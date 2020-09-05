package com.example.helpers

import android.util.Log

class DeviceManager {
    companion object {
        val TAG = DeviceManager::class.simpleName

        fun getDeviceVersion(): Int {
            Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
            return android.os.Build.VERSION.SDK_INT
        }
    }
}