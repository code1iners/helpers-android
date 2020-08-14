package com.example.helpers

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class VideoUtility {
    companion object {
        // note. thumbnail manager definition
        class ThumbnailManager {
            companion object {
                val TAG = ThumbnailManager::class.simpleName
                val listener: BooleanVariable = BooleanVariable()
                var directory: File? = null
                var fileList: ArrayList<File>? = null
                var fileExtension = ".png"
                var fileUnit = "sec"

                fun extract(filePath: String) {
                    fileList = ArrayList()
                    val manager: MediaMetadataRetriever = MediaMetadataRetriever()
                    manager.setDataSource(filePath)
                    val duration = manager.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                    val durationBySec = duration.toInt() / 1000
                    val division = durationBySec / 2
                    val timeList: ArrayList<Int> = ArrayList()
                    timeList.add(division - division / 2)
                    timeList.add(division)
                    timeList.add(division + division / 2)
                    Log.i(TAG, "duration:$duration, durationBySec:$durationBySec")

                    // note. path null check
                    if (!directory!!.exists()) directory!!.mkdirs()

                    Thread {
                        for (sec in timeList) {
                            // note. set file name
                            val fileName = FileManager.Companion.GenerateFileName.asTime() + sec + Companion.fileUnit + Companion.fileExtension
                            val file = File(directory, fileName)
                            file.createNewFile()
                            fileList!!.add(file)
                            Log.d(TAG, "moment:${(sec * 1000000).toLong()}")
                            val bitmap = manager.getFrameAtTime((sec * 1000000).toLong(), MediaMetadataRetriever.OPTION_CLOSEST)

                            val bos = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.PNG, 90, bos)
                            val bitmapData = bos.toByteArray()

                            val fos = FileOutputStream(file)
                            fos.write(bitmapData)
                            fos.flush()
                            fos.close()
                        }
                        listener.isBoo = true
                    }.start()
                }
            }
        }
    }
}