package com.example.helpers

import android.util.Log
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class DataCreatedParser {
    companion object {
        val TAG = DataCreatedParser::class.simpleName
    }

    // note. result var
    lateinit var result: String

    // note. tool vars
    var second: Long? = null
    var minute: Long? = null
    var hour: Long? = null
    var day: Long? = null
    var month: Long? = null
    var year: Long? = null

    fun parse(input: String): String?{
        Log.w(TAG, object: Any(){}.javaClass.enclosingMethod!!.name)

        this.result = ""
        var inputDate = input
        try {
            if (inputDate != "") {
                if (inputDate.contains("\"")) inputDate = inputDate.replace("\"", "")

                val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

                val created = format.parse(inputDate)
                val current = format.parse(format.format(Date()))

                this.second = TimeUnit.MILLISECONDS.toSeconds(current.time - created.time)
                this.minute = TimeUnit.MILLISECONDS.toMinutes(current.time - created.time)
                this.hour = TimeUnit.MILLISECONDS.toHours(current.time - created.time)
                this.day = TimeUnit.MILLISECONDS.toDays(current.time - created.time)
                this.month = this.day!! / 30
                this.year = this.month!! / 12

                if (this.minute!! >= 60) {
                    if (this.hour!! >= 24) {
                        if (this.day!! >= 30) {
                            if (this.month!! >= 12) {
                                result = this.year.toString() + "년 전"
                            } else {
                                result = this.month.toString() + "달 전"
                            }
                        } else {
                            result = this.day.toString() + "일 전"
                        }
                    } else {
                        result = this.hour.toString() + "시간 전"
                    }
                } else {
                    result = if (this.minute!! < 1) "방금" else this.minute.toString() + "분 전"
                }
            }
        } catch (e: Exception) {e.printStackTrace()}

        return this.result
    }
}