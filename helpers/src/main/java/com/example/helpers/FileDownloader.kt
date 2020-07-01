package com.example.helpers

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.File

class FileDownloader(private val context: Context) {
    private val TAG = FileDownloader::class.simpleName
    private var downloadId: Long = -1L
    private lateinit var downloadManager: DownloadManager

    fun init() {
        downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun download(url: String, dirName: String, fileName: String): String {
        Log.w(TAG, object: Any(){}.javaClass.enclosingMethod!!.name)
        val file = File(dirName, fileName)
        if (!file.exists()) {
            val request = DownloadManager.Request(Uri.parse(url))
                    .setTitle("Downloading a video")
                    .setDescription("Downloading test video")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                    .setDestinationUri(Uri.fromFile(file))
                    .setRequiresCharging(false)
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(true)

            downloadId = downloadManager.enqueue(request)
            Log.i(TAG, "path : ${file.path}")
        }
        return file.absolutePath
    }
}