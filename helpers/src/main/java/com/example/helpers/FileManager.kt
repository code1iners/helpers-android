package com.example.helpers

import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import com.jakewharton.threetenabp.AndroidThreeTen
import org.threeten.bp.LocalDateTime
import java.io.File
import java.lang.Exception

class FileManager {
    companion object {
        private val TAG = FileManager::class.java.simpleName

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
            try {
                val dir = File(dirPath)
                if (!dir.exists()) {
                    dir.mkdirs()
                    Log.e(TAG, "Created directory : $dir")
                }
            } catch (e: Exception) {e.printStackTrace()}
        }

        fun removeDir(dirPath: String?) {
            Log.w(TAG, object : Any(){}.javaClass.enclosingMethod!!.name)
            try {
                // note. null check
                if (dirPath.isNullOrEmpty()) return
                // note. set directory
                val dir = File(dirPath)
                if (dir.exists()) {
                    val childFiles = dir.listFiles()
                    for (file in childFiles) {
                        if (file.isDirectory) { removeDir(file.absolutePath) }
                        else file.delete()
                    }
                    // note. delete dir
                    dir.delete()
                }
            } catch (e: Exception) {e.printStackTrace()}
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

        fun getVideoPathByUri(activity: Activity, uri: Uri): String? {
            Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
            var result = ""
            try {
                val proj = arrayOf(MediaStore.Video.Media.DATA)
                val cursor = activity.contentResolver.query(uri, proj, null, null, null)
                activity.startManagingCursor(cursor)
                val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                cursor?.moveToFirst()
                result = cursor?.getString(columnIndex!!)!!
            } catch (e: Exception) {e.printStackTrace()}
            return result
        }

        fun getImagePathByUri(activity: Activity, uri: Uri): String? {
            Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
            var result = ""
            try {
                val proj = arrayOf(MediaStore.Images.Media.DATA)
                val cursor = activity.contentResolver.query(uri, proj, null, null, null)
                activity.startManagingCursor(cursor)
                val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                cursor?.moveToFirst()
                result = cursor?.getString(columnIndex!!)!!
            } catch (e: Exception) {e.printStackTrace()}
            return result
        }

        class GenerateFileName(private val context: Context) {
            init {
                AndroidThreeTen.init(context)
            }
            companion object {
                fun asTime(): String? {
                    var result: String? = null
                    try {
                        val time = LocalDateTime.now()
                        val year = time.year.toString()
                        val month = if (time.monthValue.toString().length < 2) "0" + time.monthValue.toString() else time.monthValue.toString()
                        val day = if (time.dayOfMonth.toString().length < 2) "0" + time.dayOfMonth.toString() else time.dayOfMonth.toString()
                        val hour = if (time.hour.toString().length < 2) "0" + time.hour.toString() else time.hour.toString()
                        val minute = if (time.minute.toString().length < 2) "0" + time.minute.toString() else time.minute.toString()
                        val second = if (time.second.toString().length < 2) "0" + time.second.toString() else time.second.toString()
                        result = year + month + day + hour + minute + second + "_"
                    } catch (e: Exception) {e.printStackTrace()}
                    return result
                }
            }
        }
    }
}