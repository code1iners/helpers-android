package com.example.helpers

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import java.io.File

class FileManager {
    fun removeFile(filePath: String?) {
        Log.w(TAG, object : Any(){}.javaClass.enclosingMethod!!.name)
        Log.i(TAG, "filePath : $filePath")

        val file = File(filePath)
        Log.v(TAG, "file : $file")
        if (file.exists()) {
            if (file.delete()) Log.i(TAG, "File delete success")
            else Log.i(TAG, "File delete fail")
        }
    }

    fun createDir(dirPath: String?) {
        Log.w(TAG, object : Any(){}.javaClass.enclosingMethod!!.name)
        val dir = File(dirPath)
        if (!dir.exists()) {
            dir.mkdirs()
            Log.e(TAG, "Created directory : $dir")
        }
    }

    fun getFileNameFromUri(context: Context, uri: Uri): String {
        Log.w(TAG, object : Any(){}.javaClass.enclosingMethod!!.name)
        var result: String? = null
        if (uri.scheme.equals("content")) {
            val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            } finally {
                cursor!!.close()
            }
        }
        if (result == null) {
            result = uri.path
            val cut = result?.lastIndexOf('/')
            if (cut != -1) {
                result = result?.substring(cut!! + 1)
            }
        }
        result = result!!.replace(" ", "_")

        return result
    }

    companion object {
        private val TAG = FileManager::class.java.simpleName
    }
}