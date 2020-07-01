package com.example.helpers

import android.graphics.Bitmap
import android.util.Log
import wseemann.media.FFmpegMediaMetadataRetriever

class FirstFrameHunter {
    private val TAG = FirstFrameHunter::class.simpleName

    fun getFrame(videoPath: String): Bitmap {
        Log.w(TAG, object: Any(){}.javaClass.enclosingMethod!!.name)

        val retriever = FFmpegMediaMetadataRetriever()
        retriever.setDataSource(videoPath)

        return retriever.getFrameAtTime(1000)
    }
}