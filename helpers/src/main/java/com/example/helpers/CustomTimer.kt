package com.example.helpers

import android.os.Handler
import android.util.Log
import android.widget.TextView
import java.util.*

class CustomTimer {

    // note. increase
    class Increase : TimerTask() {
        private val TAG = Increase::class.simpleName

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

    // note. decrease
    class Decrease : TimerTask() {
        private val TAG = Decrease::class.simpleName

        // note. values
        private var second: Int = 0
        private var minute: Int = 0
        private var hour: Int = 0

        // note. views
        lateinit var viewSecond: TextView
        lateinit var viewMinute: TextView
        lateinit var viewHour: TextView

        // note. other vars
        private lateinit var handler: Handler
        private val cycleTime: Long = 1000

        fun init() {
            Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
            try {
                handler = Handler()

                Handler().post {
                    viewSecond.text = "00"
                    viewMinute.text = "00"
                    viewHour.text = "00"
                }

            } catch (e: Exception) {e.printStackTrace()}
        }

        fun setViews(second: TextView, minute: TextView, hour: TextView) {
            try {
                this.viewSecond = second
                this.viewMinute = minute
                this.viewHour = hour
            } catch (e: Exception) {e.printStackTrace()}
        }

        fun setLimitTimeByMinute(minute: Int) {
            try  {
                // note. set second by minute
                second = minute * 60

            } catch (e: Exception) {e.printStackTrace()}
        }

        fun start() {
            Timer().schedule(this, 0, cycleTime)

        }

        override fun run() {
            try {
                if (second <= 0) return
                // note. decrease second value
                second--

                // note. set values
                setSecond()
                setMinute()
                setHour()
            } catch (e: Exception) {e.printStackTrace()}
        }

        private fun setSecond() {
            try {
                this.second = second % 60
                Log.i(TAG, "second:${this.second}")
                handler.post{
                    if (this.second < 10) this.viewSecond.text = "0$second"
                    else this.viewSecond.text = "$second"
                }
            } catch (e: Exception) {e.printStackTrace()}
        }

        private fun setMinute() {
            try {
                this.minute = second / 60
                Log.i(TAG, "minute:${this.minute}")
                handler.post{
                    if (this.minute < 10) this.viewMinute.text = "0$minute"
                    else this.viewMinute.text = "$minute"
                }
            } catch (e: Exception) {e.printStackTrace()}
        }

        private fun setHour() {
            try {
                this.hour = this.minute % 60
                Log.i(TAG, "hour:${this.hour}")
                handler.post{
                    if (this.hour < 10) this.viewHour.text = "0$hour"
                    else this.viewHour.text = "$hour"
                }
            } catch (e: Exception) {e.printStackTrace()}
        }
    }
}