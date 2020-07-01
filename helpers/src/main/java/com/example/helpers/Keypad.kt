package com.example.helpers

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class Keypad(private val activity: Activity) {
    private var imm: InputMethodManager? = null

    private fun init() {
        Log.w(TAG, object{}.javaClass.enclosingMethod!!.name)
        imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    fun down(view: View) {
        Log.w(TAG, object{}.javaClass.enclosingMethod!!.name)
        imm!!.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun up(field: EditText) {
        Log.w(TAG, object{}.javaClass.enclosingMethod!!.name)
        field.requestFocus()
        imm!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    companion object {
        private val TAG = Keypad::class.java.simpleName
    }

    init {
        init()
    }
}