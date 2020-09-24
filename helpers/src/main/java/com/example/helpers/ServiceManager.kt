package com.example.helpers

import android.app.Activity
import android.content.Intent
import android.util.Log

class ServiceManager {
  companion object {
    val TAG = ServiceManager::class.simpleName
    fun start(activity: Activity, service: Any) {
      try {
        Intent(activity, service::class.java).also { intent ->
          Log.i(TAG, "start service..")
          activity.startService(intent)
        }
      } catch (e: Exception) {e.printStackTrace()}
    }
  }
}