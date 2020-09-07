package com.example.helpers

import android.os.Handler
import android.util.Log
import android.widget.TextView
import java.util.*

class CustomTimer_bak : TimerTask() {
    private val TAG = CustomTimer_bak::class.simpleName

    // note. for initialization
    var firstTime: Boolean = false
    var restart: Boolean = false
    var running: Boolean = false

    // note. value
    private var sec: Int = 0
    private var min: Int = 0
    private var hour: Int = 0

    // note. views
    lateinit var viewSecond: TextView
    lateinit var viewMinute: TextView
    lateinit var viewHour: TextView

    // note. handler
    private lateinit var handler: Handler

    fun init() {
        Log.w(TAG, object : Any() {}.javaClass.enclosingMethod!!.name)
        try {
            handler = Handler()

            Handler().post {
                viewSecond.text = "00"
                viewMinute.text = "00"
                viewHour.text = "00"
            }
        } catch (e: Exception) {e.printStackTrace()}
    }

    fun setViews(sec: TextView, min: TextView, hour: TextView) {
        try {
            this.viewSecond = sec
            this.viewMinute = min
            this.viewHour = hour
        } catch (e: Exception) {e.printStackTrace()}
    }

    fun start(cycleTime: Long) {
        if (this.running) this.init()
        else Timer().schedule(this, 0, cycleTime)
    }

    override fun run() {

        try {
            if (!firstTime) {
                running = true
                firstTime = true
            }

            sec++
            if (sec > 59) {
                sec = 0
                min++
                // note. update minutes
                if (min < 10) {
                    handler.post(Runnable { viewMinute.text = "0$min" })
                } else {
                    handler.post(Runnable { viewMinute.text = min.toString() })
                }
                if (min > 59) {
                    min = 0
                    hour++
                    // note. update hours
                    if (hour < 10) {
                        handler.post(Runnable {
                            viewHour.text = "0$hour"
                            viewMinute.text = "0$min"
                        })
                    } else {
                        handler.post(Runnable {
                            viewHour.text = hour.toString()
                            viewMinute.text = "0$min"
                        })
                    }
                    if (hour > 23) {
                        init()
                    }
                }
            } else {
                // note. update seconds
                if (sec < 10) {
                    handler.post(Runnable { viewSecond.text = "0$sec" })
                } else {
                    handler.post(Runnable { viewSecond.text = sec.toString() })
                }
            }
        } catch (e: Exception) {e.printStackTrace()}
    }
}