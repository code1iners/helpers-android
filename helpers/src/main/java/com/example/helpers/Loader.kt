package com.example.helpers

import android.app.ProgressDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.util.Log

class Loader(context: Context) : AsyncTask<String?, Void?, String>() {
    private val dialog: ProgressDialog = ProgressDialog(context)
    private var loop = true

    fun setProgress(value: Int) {
        dialog.progress = value
    }

    fun setIcon(drawable: Drawable) {
        dialog.setIcon(drawable)
    }

    fun setTitle(title: String) {
        dialog.setTitle(title)
    }

    fun setMessage(message: String) {
        dialog.setMessage(message)
    }

    fun setCancelable(flag : Boolean) {
        dialog.setCanceledOnTouchOutside(flag)
        dialog.setCancelable(flag)
    }

    fun off() {
        Log.w(TAG, object: Any(){}.javaClass.enclosingMethod!!.name)

        loop = false
    }

    public override fun onPreExecute() {
        Log.w(TAG, object : Any(){}.javaClass.enclosingMethod!!.name)

        loop = true
        dialog.show()
        super.onPreExecute()
    }

    override fun doInBackground(vararg strings: String?): String {
        Log.w(TAG, object : Any(){}.javaClass.enclosingMethod!!.name)

        try {
            while (loop) {
                Log.i(TAG, "looping : $loop")
                Thread.sleep(1000)
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return "Success"
    }

    public override fun onPostExecute(s: String) {
        Log.w(TAG, object : Any(){}.javaClass.enclosingMethod!!.name)

//        super.onPostExecute(s)
        dialog.dismiss()
    }

    companion object {
        private val TAG = Loader::class.java.simpleName
    }

    init {
        Log.w(TAG, "init")
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        dialog.setMessage("Loading...")
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
    }
}