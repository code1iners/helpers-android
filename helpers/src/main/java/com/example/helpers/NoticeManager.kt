package com.example.helpers

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun View.snackbarForShort(message: String) {
    Snackbar.make(
            this,
            message,
            Snackbar.LENGTH_SHORT
    ).also { snackbar ->
        snackbar.setAction("OK") {
            snackbar.dismiss()
        }
    }.show()
}

fun View.snackbarForLong(message: String) {
    Snackbar.make(
            this,
            message,
            Snackbar.LENGTH_LONG
    ).also { snackbar ->
        snackbar.setAction("OK") {
            snackbar.dismiss()
        }
    }.show()
}

fun View.snackbarForIndefinite(message: String) {
    Snackbar.make(
            this,
            message,
            Snackbar.LENGTH_INDEFINITE
    ).also { snackbar ->
        snackbar.setAction("OK") {
            snackbar.dismiss()
        }
    }.show()
}

fun Context.toastForShort(message: String) {
    Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
    ).show()
}

fun Context.toastForLong(message: String) {
    Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
    ).show()
}