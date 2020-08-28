package com.example.helpers

import android.app.Activity
import android.view.WindowManager

class ScreenManager {
    companion object {
        val TAG = ScreenManager::class.simpleName

        fun alwaysOn(activity: Activity) {
            try {
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
            } catch (e: Exception) {e.printStackTrace()}
        }

        fun alwaysOnWhenLocked(activity: Activity) {
            try {
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
            } catch (e: Exception) {e.printStackTrace()}
        }
    }
}