package com.example.helpers

import android.app.Activity
import android.app.ProgressDialog
import android.util.Log
import com.daasuu.mp4compose.FillMode
import com.daasuu.mp4compose.Rotation
import com.daasuu.mp4compose.composer.Mp4Composer

class VideoRotateSaver(private val activity: Activity) {
    private val TAG: String? = VideoRotateSaver::class.simpleName
    private lateinit var loading: ProgressDialog
    var fileName = "temp.mp4"
    lateinit var bv: BooleanVariable
    lateinit var rotation: Rotation


    //val destPath = DataPassProtocol.CACHE_PATH_MP4_DIR + File.separator + DataPassProtocol.CACHE_PATH_MP4_FILE_RESULT
    var destPath = "DataPassProtocol.CACHE_PATH_MP4_DIR + File.separator  + fileName"
    var flag = 0

    fun init () {
        loading = ProgressDialog(activity)
        loading.setCancelable(false)
        loading.setCanceledOnTouchOutside(false)
        loading.setMessage("Loading...")
        loading.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)

        bv = BooleanVariable()
    }

    fun rotate (srcPath: String) {
        Log.w(TAG, object: Any(){}.javaClass.enclosingMethod!!.name)
        Log.i(TAG, "srcPath : $srcPath")
        Log.i(TAG, "flag : $flag")

        if (flag > 3) flag = 0

        if (flag == 0) rotation = Rotation.ROTATION_90
        else if (flag == 1) rotation = Rotation.ROTATION_180
        else if (flag == 2) rotation = Rotation.ROTATION_270
        else rotation = Rotation.NORMAL

        loading.progress = 0
        loading.show()
        Mp4Composer(srcPath, destPath)
                .rotation(rotation)
                .size(1280, 720)
                .fillMode(FillMode.PRESERVE_ASPECT_FIT)
                .listener(object : Mp4Composer.Listener {
                    override fun onProgress(progress: Double) {

                        loading.progress = (progress * 100).toInt()
                    }

                    override fun onCompleted() {
                        Log.w(TAG, "onCompleted : $destPath")
                        flag++
                        bv.isBoo = true
                        loading.dismiss()
                    }

                    override fun onCanceled() {
                        Log.w(TAG, "onCanceled")
                        loading.dismiss()
                    }

                    override fun onFailed(exception: Exception) {
                        Log.wtf(TAG, "onFailed : $exception")
                        loading.dismiss()
                    }
                })
                .start()
    }
}