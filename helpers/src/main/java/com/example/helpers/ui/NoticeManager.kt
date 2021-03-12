package com.example.helpers.ui

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun View.snackingIndefinite(messageId: Int) {
    snackingIndefinite(context.getString(messageId))
}

fun View.snackingIndefinite(message: String) {
    snacking(message, Snackbar.LENGTH_INDEFINITE, null) {}
}

fun View.snackingLong(messageId: Int) {
    snackingLong(context.getString(messageId))
}

fun View.snackingLong(message: String) {
    snacking(message, Snackbar.LENGTH_LONG, null) {}
}

fun View.snackingShort(messageId: Int) {
    snackingShort(context.getString(messageId))
}

fun View.snackingShort(message: String) {
    snacking(message, Snackbar.LENGTH_SHORT, null) {}
}

fun View.snacking(messageId: Int, length: Int) {
    snacking(context.getString(messageId), length)
}

fun View.snacking(message: String, length: Int) {
    snacking(message, length, null) {}
}

fun View.snacking(
        messageId: Int,
        length: Int,
        actionMessageId: Int,
        action: (View) -> Unit
) {
    snacking(context.getString(messageId), length, context.getString(actionMessageId), action)
}

fun View.snacking(
        message: String,
        length: Int,
        actionMessage: CharSequence?,
        action: (View) -> Unit
) {
    val snackbar = Snackbar.make(this, message, length)
    if (actionMessage != null) {
        snackbar.setAction(actionMessage) {
            action(this)
        }.show()
    }
}

fun Context.toastingShort(message: String) {
    toasting(message, Toast.LENGTH_SHORT)
}

fun Context.toastingLong(message: String) {
    toasting(message, Toast.LENGTH_LONG)
}

fun Context.toasting(message: String, length: Int) {
    Toast.makeText(
            this,
            message,
            length
    ).show()
}