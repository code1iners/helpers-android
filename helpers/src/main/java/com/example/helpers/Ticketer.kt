package com.example.helpers

import android.util.Log

class Ticketer {
    companion object {
        private val TAG = Ticketer::class.java.simpleName
        var map = HashMap<String, String>()

        fun add(key: String?, value: String?) {
            Log.w(TAG, object: Any(){}.javaClass.enclosingMethod!!.name)
            Log.i(TAG, "key : ${key!!}, value : ${value!!}")

            map.put(key, value)
            check()
        }

        fun remove(key: String?) {
            Log.w(TAG, object: Any(){}.javaClass.enclosingMethod!!.name)
            Log.i(TAG, "key : ${key!!}")
            map.remove(key)
            check()
        }

        fun get(key: String?): String? {
            Log.w(TAG, object: Any(){}.javaClass.enclosingMethod!!.name)

            var result: String? = null

            Log.i(TAG, "key : ${key}, constain : ${map.containsKey(key)}")
            if (map.containsKey(key)) result = map.get(key)
            else result = null
            Log.v(TAG, "result : ${result}")

            return result
        }

        fun check() {
            Log.w(TAG, object: Any(){}.javaClass.enclosingMethod!!.name)
            for (item in map.keys) Log.v(TAG, "${item}")
        }
    }
}