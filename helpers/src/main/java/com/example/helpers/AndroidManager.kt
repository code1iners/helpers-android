package com.example.helpers

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun Context.getAppVersion(): String {
    val info = this.packageManager.getPackageInfo(this.packageName, 0)
    return info.versionName
}

fun Activity.getKeyHash() {
    var packageInfo: PackageInfo? = null
    try {
        packageInfo = this.packageManager.getPackageInfo(this.packageName, PackageManager.GET_SIGNATURES)
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    if (packageInfo == null) Log.e("KeyHash", "KeyHash:null")
    for (signature in packageInfo!!.signatures) {
        try {
            val md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.v("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
        } catch (e: NoSuchAlgorithmException) {
            Log.e("KeyHash", "Unable to get MessageDigest. signature=$signature", e)
        }
    }
}