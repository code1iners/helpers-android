package com.example.helpers

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar

class PermissionManager {
    object RequestCode {
        const val WRITE_EXTERNAL_STORAGE = 100
        const val INTERNET = 101
        const val CAMERA = 102
        const val READ_EXTERNAL_STORAGE = 103
        const val RECORD_AUDIO = 104
        const val BROADCAST_STICKY = 105
        const val MODIFY_AUDIO_SETTINGS = 106
        const val BLUETOOTH = 107
        const val BLUETOOTH_ADMIN = 108
        const val ACCESS_COARSE_LOCATION = 109
        const val REQUEST_IGNORE_BATTERY_OPTIMIZATIONS = 110
        const val RECEIVE_BOOT_COMPLETED = 111
        const val FOREGROUND_SERVICE = 112
        const val WAKE_LOCK = 113
        const val ACCESS_NETWORK_STATE = 114
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
                Log.e(TAG, "##1:${activity.shouldShowRequestPermissionRationale(permission)}")
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
                    showDialogPermission(activity, permission)
                }
            } catch (e: Exception) {e.printStackTrace()}
        }

        fun showDialogPermission(activity: Activity, permission: String) {
            Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
            try {
                val builder = AlertDialog.Builder(activity)
                builder.setTitle("권한 요청")

                when (permission) {
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE -> { builder.setMessage("저장 공간 쓰기 권한 승인이 필요합니다.") }
                    android.Manifest.permission.INTERNET -> { builder.setMessage("인터넷 권한 승인이 필요합니다.") }
                    android.Manifest.permission.CAMERA -> { builder.setMessage("카메라 권한 승인이 필요합니다.") }
                    android.Manifest.permission.READ_EXTERNAL_STORAGE -> { builder.setMessage("저장 공간 읽기 권한 승인이 필요합니다.") }
                    android.Manifest.permission.RECORD_AUDIO -> { builder.setMessage("음성 녹음 권한 승인이 필요합니다.") }
                    android.Manifest.permission.BROADCAST_STICKY -> { builder.setMessage("브로드캐스트 권한 승인이 필요합니다.") }
                    android.Manifest.permission.MODIFY_AUDIO_SETTINGS -> { builder.setMessage("오디오 설정 변경 권한 승인이 필요합니다.") }
                    android.Manifest.permission.BLUETOOTH -> { builder.setMessage("블루투스 권한 승인이 필요합니다.") }
                    android.Manifest.permission.BLUETOOTH_ADMIN -> { builder.setMessage("블루투스 관리자 권한 승인이 필요합니다.") }
                    android.Manifest.permission.ACCESS_COARSE_LOCATION -> { builder.setMessage("위치 권한 승인이 필요합니다.") }
                    android.Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS -> { builder.setMessage("배터리 최적화 무시 권한 승인이 필요합니다.") }
                    android.Manifest.permission.RECEIVE_BOOT_COMPLETED -> { builder.setMessage("부팅 시스템 권한 승인이 필요합니다.") }
                    android.Manifest.permission.FOREGROUND_SERVICE -> { builder.setMessage("백그라운드 실행 권한 승인이 필요합니다.") }
                    android.Manifest.permission.WAKE_LOCK -> { builder.setMessage("시스템 권한 승인이 필요합니다.") }
                    android.Manifest.permission.ACCESS_NETWORK_STATE -> { builder.setMessage("네트워크 접근 권한 승인이 필요합니다.") }
                }
                builder.setPositiveButton("확인") { dialogInterface, i ->
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", activity.packageName, null))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    activity.startActivity(intent)
                }
                builder.setNegativeButton("나중에") { dialogInterface, i ->

                }
                val dialog = builder.create()
                dialog.show()
            } catch (e: Exception) {e.printStackTrace()}
        }
    }
}