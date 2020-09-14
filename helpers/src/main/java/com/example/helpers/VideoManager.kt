package com.example.helpers

import android.app.Activity
import android.app.ProgressDialog
import android.media.MediaMetadataRetriever
import android.util.Log
import android.widget.Toast
import com.daasuu.mp4compose.FillMode
import com.daasuu.mp4compose.Rotation
import com.daasuu.mp4compose.composer.Mp4Composer
import java.io.File

class VideoManager {

    class Rotate(private val activity: Activity) {

        // note. file path
        var inputFilePath: String? = null
        var outputDirPath: String? = null
        var outputFilePath: String? = null
        var resultFilePath: String? = null
        // note. listener
        var onRotateListener: OnRotateListener? = null
        // note. dialog
        var dialogTitle: String = "The video is rotating.."
        var dialogMessage: String? = null

        fun start() {
            // note. get video's changed angle
            val angle = getVideoChangedAngle()

            // note. checking non-null
            if (angle == -1) {
                Log.e(TAG, "Angle value is not exist")
                return
            }

            // note. rotate
            rotate(angle)
        }

        private fun getVideoChangedAngle(): Int {
            Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)

            if (inputFilePath.isNullOrBlank()) {
                Toast.makeText(activity, "파일을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                return -1
            }

            var angle: Int = -1
            val retriever: MediaMetadataRetriever = MediaMetadataRetriever()
            try {
                retriever.setDataSource(inputFilePath)
                angle = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION).toInt()

            } catch (e: Exception) {e.printStackTrace()}

            Log.d(TAG, "angle:$angle")
            return angle
        }

        private fun rotate(angle: Int) {
            if (angle == 0) onRotateListener!!.rotateCompleted(null)
            else createRotatedNewFile()
        }

        private fun createRotatedNewFile() {
            Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)

            try {
                Log.i(TAG, "outputDirPath:$outputDirPath, outputFilePath:$outputFilePath")
                if (!File(outputDirPath).exists()) {
                    File(outputDirPath).mkdirs()
                }

                // note. init progress dialog
                val d = ProgressDialog(activity)
                d.setTitle(dialogTitle)
                if (!dialogMessage.isNullOrBlank()) d.setMessage(dialogMessage)
                d.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                d.setCanceledOnTouchOutside(false)
                d.setCancelable(false)
                d.show()

                // note. set result file path
                resultFilePath = outputDirPath + File.separator + outputFilePath
                Log.i(TAG, "resultFilePath:$resultFilePath")
                Mp4Composer(inputFilePath!!, resultFilePath!!)
                        .rotation(Rotation.NORMAL)
                        .size(1280, 720)
                        .fillMode(FillMode.PRESERVE_ASPECT_FIT)
                        .listener(object: Mp4Composer.Listener {
                            override fun onProgress(progress: Double) {
                                Log.d(TAG, "progress:$progress")
                                d.progress = (progress * 100).toInt()
                            }

                            override fun onCompleted() {
                                Log.i(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
                                try {
                                    onRotateListener!!.rotateCompleted(resultFilePath!!)
                                    d.dismiss()
                                } catch (e: Exception) {e.printStackTrace()}
                            }

                            override fun onCanceled() {
                                Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
                                try {
                                    onRotateListener!!.rotateFailed()
                                    d.dismiss()
                                } catch (e: Exception) {e.printStackTrace()}
                            }

                            override fun onFailed(exception: java.lang.Exception?) {
                                Log.e(TAG, "${object:Any(){}.javaClass.enclosingMethod!!.name}\n$exception")
                                d.dismiss()
                            }

                        })
                        .start()

            } catch (e: Exception) {e.printStackTrace()}
        }

        interface OnRotateListener {
            fun rotateCompleted(resultFilePath: String?)
            fun rotateFailed()
        }

        fun clear() {
            // note. check file path is not exist
            if (resultFilePath.isNullOrBlank()) return
            try {
                File(resultFilePath).delete()
            } catch (e: Exception) {e.printStackTrace()}
        }

        companion object {
            val TAG = VideoManager::class.simpleName
        }
    }
}