package com.example.helpers

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.util.Log

class DeviceManager {
    companion object {
        val TAG = DeviceManager::class.simpleName

        fun getDeviceVersion(): Int {
            Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
            return android.os.Build.VERSION.SDK_INT
        }
        
        fun getDeviceId(context: Context): String {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }
    
        fun getDeviceModel(): String {
            return Build.MODEL
        }
    
        fun getDeviceOs(): String {
            return Build.VERSION.RELEASE.toString()
        }
    
        fun getAppVersion(context: Context): String {
            val info = context.packageManager.getPackageInfo(context.packageName, 0)
            return info.versionName
        }
    }
}