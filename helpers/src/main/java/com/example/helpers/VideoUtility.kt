package com.example.helpers

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class VideoUtility {
    companion object {

        class ThumbnailManager {
            companion object {
                val TAG = ThumbnailManager::class.simpleName
                val listener: BooleanVariable = BooleanVariable()
                var directory: File? = null
                var fileList: ArrayList<File>? = null
                // note. const vars
                val FILE_EXTENSION_PNG = ".png"
                val FILE_UNIT_SEC = "sec"
                fun thumbnailExtract() {
                    fun extract(directoryPath: String, filePath: String) {
                        fileList = ArrayList()
                        val manager: MediaMetadataRetriever = MediaMetadataRetriever()
                        manager.setDataSource(filePath)
                        val duration = manager.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                        val durationBySec = duration.toInt() / 1000
                        Log.i(TAG, "duration:$duration, durationBySec:$durationBySec")

                        // note. path null check
                        if (directoryPath.isNullOrEmpty()) return

                        // note. set file directory
                        directory = File(directoryPath)
                        directory!!.mkdirs()

                        Thread {
                            for (sec in 0 until durationBySec) {
                                // note. set file name
                                val fileExtension = FILE_EXTENSION_PNG
                                val fileUnit = FILE_UNIT_SEC
                                val fileName = FileManager.Companion.GenerateFileName.asTime() + sec + fileUnit + fileExtension
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
}