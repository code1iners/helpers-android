package com.example.helpers

import android.util.Log

class HelloKotlin {
    private val TAG = HelloKotlin::class.simpleName

    fun hello() {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)


    }
}