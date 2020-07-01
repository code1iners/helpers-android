package com.example.helpers

import android.media.ExifInterface
import android.util.Log
import java.io.IOException

class ImageRotationDetector {
    fun checker(filePath: String?): Int {
        Log.w(TAG, object: Any(){}.javaClass.simpleName)
        var rotate = 0

        try {
            val e = ExifInterface(filePath)
            val orientation = e.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            Log.i(TAG, "orientation : $orientation")

            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> {
                    Log.v(TAG, "90")
                    rotate = 90
                }

                ExifInterface.ORIENTATION_ROTATE_180 -> {
                    Log.v(TAG, "180")
                    rotate = 180
                }

                ExifInterface.ORIENTATION_ROTATE_270 -> {
                    Log.v(TAG, "270")
                    rotate = 270
                }

                else -> {
                    Log.v(TAG, "0")
                    rotate = 0
                }
            }
        }
        catch (ex: IOException) {
            ex.printStackTrace()
        }

        return rotate
    }

    companion object {
        private val TAG = ImageRotationDetector::class.java.simpleName
    }
}