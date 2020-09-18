package com.example.helpers

import android.app.Activity
import android.os.Build
import android.view.View
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

        // note. top status & bottom nav bar (full screen mode)
        fun setSystemUI(activity: Activity, status: Int) {
            try {
                when (status) {
                    View.GONE -> {
                        // note. Enables regular immersive mode.
                        // note. For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
                        // note. Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        activity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                                // note. Set the content to appear under the system bars so that the
                                // note. content doesn't resize when the system bars hide and show.
                                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                // note. Hide the nav bar and status bar
                                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                or View.SYSTEM_UI_FLAG_FULLSCREEN)
                    }

                    // note. Shows the system bars by removing all the flags
                    // note. except for the ones that make the content appear under the system bars.
                    View.VISIBLE -> {
                        activity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                    }
                }
            } catch (e: Exception) {e.printStackTrace()}
        }
    }
}