package com.example.helpers

import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class PermissionManager {
    companion object {
        val TAG = PermissionManager::class.simpleName
        val REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 100
        val REQUEST_CODE_INTERNET = 101
        val REQUEST_CODE_CAMERA = 102
        val REQUEST_CODE_READ_EXTERNAL_STORAGE = 103
        val REQUEST_CODE_RECORD_AUDIO = 104
        val REQUEST_CODE_BROADCAST_STICKY = 105
        val REQUEST_CODE_MODIFY_AUDIO_SETTINGS = 106
        val REQUEST_CODE_BLUETOOTH = 107
        val REQUEST_CODE_BLUETOOTH_ADMIN = 108
        val REQUEST_CODE_ACCESS_COARSE_LOCATION = 109
        val REQUEST_CODE_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS = 110
        val REQUEST_CODE_RECEIVE_BOOT_COMPLETED = 111
        val REQUEST_CODE_FOREGROUND_SERVICE = 112
        val REQUEST_CODE_WAKE_LOCK = 113
        val REQUEST_CODE_ACCESS_NETWORK_STATE = 114

        fun checkPermission(activity: Activity, view: View, permission: String): Boolean {
            Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
            try {
                if (activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "이미 권한을 가지고 있습니다.")
                    return true
                }
                else {
                    Log.e(TAG, "권한이 없습니다.")
                    requestPermission(activity, view, permission)
                    return false
                }
            } catch (e: Exception) {e.printStackTrace()}
            return false
        }

        fun requestPermission(activity: Activity, view: View, permission: String) {
            Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
            try {
                if (activity.shouldShowRequestPermissionRationale(permission)) {
                    val snack = Snackbar.make(view, "해당 기능을 사용하기 위해서는 권한이 필요합니다.", Snackbar.LENGTH_INDEFINITE)
                    snack.setAction("설정") {
                        activity.requestPermissions(arrayOf(permission), REQUEST_CODE_WRITE_EXTERNAL_STORAGE)
                        snack.dismiss()
                    }
                    snack.show()
                } else {
                    Snackbar.make(view, "권한에 대한 승인이 거부되었습니다.\n앱 정보에서 해당 권한을 직접 설정해주세요.", Snackbar.LENGTH_LONG).show()
                }
            } catch (e: Exception) {e.printStackTrace()}
        }
    }
}