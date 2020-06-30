package com.example.helpers

import android.util.Log

class HelloKotilin {
    private val TAG = HelloKotilin::class.simpleName

    fun hello() {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)


    }
}