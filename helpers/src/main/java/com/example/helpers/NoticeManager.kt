package com.example.helpers

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class NoticeManager {
    companion object {
        val TAG = NoticeManager::class.simpleName

        fun toastShort(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun toastLong(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        fun snackShort(view: View, message: String): Snackbar {
            return Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        }

        fun snackLong(view: View, message: String): Snackbar {
            return Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        }

        fun snackIndefinite(view: View, message: String): Snackbar {
            return Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
        }

    }
}