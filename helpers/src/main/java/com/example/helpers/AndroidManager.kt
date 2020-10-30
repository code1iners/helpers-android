package com.example.helpers

import android.content.Context
import android.os.Build
import android.provider.Settings

class AndroidManager {
	companion object {
		val TAG = AndroidManager::class.simpleName
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