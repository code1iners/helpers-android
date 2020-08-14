package com.example.helpers

import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.util.jar.Manifest

class PermissionManager {
    object RequestCode {
        val WRITE_EXTERNAL_STORAGE = 100
        val INTERNET = 101
        val CAMERA = 102
        val READ_EXTERNAL_STORAGE = 103
        val RECORD_AUDIO = 104
        val BROADCAST_STICKY = 105
        val MODIFY_AUDIO_SETTINGS = 106
        val BLUETOOTH = 107
        val BLUETOOTH_ADMIN = 108
        val ACCESS_COARSE_LOCATION = 109
        val REQUEST_IGNORE_BATTERY_OPTIMIZATIONS = 110
        val RECEIVE_BOOT_COMPLETED = 111
        val FOREGROUND_SERVICE = 112
        val WAKE_LOCK = 113
        val ACCESS_NETWORK_STATE = 114
    }

    companion object {
        val TAG = PermissionManager::class.simpleName

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
                        when (permission) {
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE -> {activity.requestPermissions(arrayOf(permission), RequestCode.WRITE_EXTERNAL_STORAGE)}
                            android.Manifest.permission.INTERNET -> {activity.requestPermissions(arrayOf(permission), RequestCode.INTERNET)}
                            android.Manifest.permission.CAMERA -> {activity.requestPermissions(arrayOf(permission), RequestCode.CAMERA)}
                            android.Manifest.permission.READ_EXTERNAL_STORAGE -> {activity.requestPermissions(arrayOf(permission), RequestCode.READ_EXTERNAL_STORAGE)}
                            android.Manifest.permission.RECORD_AUDIO -> {activity.requestPermissions(arrayOf(permission), RequestCode.RECORD_AUDIO)}
                            android.Manifest.permission.BROADCAST_STICKY -> {activity.requestPermissions(arrayOf(permission), RequestCode.BROADCAST_STICKY)}
                            android.Manifest.permission.MODIFY_AUDIO_SETTINGS -> {activity.requestPermissions(arrayOf(permission), RequestCode.MODIFY_AUDIO_SETTINGS)}
                            android.Manifest.permission.BLUETOOTH -> {activity.requestPermissions(arrayOf(permission), RequestCode.BLUETOOTH)}
                            android.Manifest.permission.BLUETOOTH_ADMIN -> {activity.requestPermissions(arrayOf(permission), RequestCode.BLUETOOTH_ADMIN)}
                            android.Manifest.permission.ACCESS_COARSE_LOCATION -> {activity.requestPermissions(arrayOf(permission), RequestCode.ACCESS_COARSE_LOCATION)}
                            android.Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS -> {activity.requestPermissions(arrayOf(permission), RequestCode.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)}
                            android.Manifest.permission.RECEIVE_BOOT_COMPLETED -> {activity.requestPermissions(arrayOf(permission), RequestCode.RECEIVE_BOOT_COMPLETED)}
                            android.Manifest.permission.FOREGROUND_SERVICE -> {activity.requestPermissions(arrayOf(permission), RequestCode.FOREGROUND_SERVICE)}
                            android.Manifest.permission.WAKE_LOCK -> {activity.requestPermissions(arrayOf(permission), RequestCode.WAKE_LOCK)}
                            android.Manifest.permission.ACCESS_NETWORK_STATE -> {activity.requestPermissions(arrayOf(permission), RequestCode.ACCESS_NETWORK_STATE)}
                        }
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