package com.example.helpers

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class PreferencesManager(activity: Activity, mode: String) {
    var prefs: SharedPreferences? = activity.getSharedPreferences(mode, Context.MODE_PRIVATE)

    fun check() {
        Log.w(TAG, object : Any() {}.javaClass.enclosingMethod!!.name)
        val all = prefs!!.all
        for ((key, value) in all) {
            Log.v(TAG, "{$key: $value}")
        }
    }

    fun clear() {
        Log.w(TAG, object : Any() {}.javaClass.enclosingMethod!!.name)
        val editor = prefs!!.edit()
        editor.clear()
        editor.apply()
        check()
    }

    fun remove(key: String) {
        Log.w(TAG, object : Any() {}.javaClass.enclosingMethod!!.name)
        Log.i(TAG, "key : $key")
        val editor = prefs!!.edit()
        editor.remove(key)
        editor.apply()
        check()
    }

    fun add(key: String, value: String) {
        Log.w(TAG, object : Any() {}.javaClass.enclosingMethod!!.name)
        Log.i(TAG, "key : $key, value : $value")
        val editor = prefs!!.edit()
        editor.putString(key, value)
        editor.apply()

        check()
    }

    operator fun get(key: String?): String? {
        Log.w(TAG, object : Any() {}.javaClass.enclosingMethod!!.name)
        val result = prefs!!.getString(key, null)
        check()
        return result
    }

    companion object {
        private val TAG = PreferencesManager::class.java.simpleName
    }
}