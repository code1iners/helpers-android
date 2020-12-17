package com.example.helpers

import android.app.Activity
import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun ContentResolver.getFileName(uri: Uri): String {
	var name = ""
	val cursor = query(uri, null, null, null, null)
	cursor?.use {
		it.moveToFirst()
		name = cursor.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
	}
	return name
}

fun String.dateFormatConvert(format: String?, locale: Locale?): String? {
	var result: String? = null
	
	try {
		if (format == null) return null
		
		val oldFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ",
				locale ?: Locale.KOREA
		)
		val newFormat = SimpleDateFormat(format, Locale.KOREA)
		
		val date = oldFormat.parse(this)
		result = newFormat.format(date)
	} catch (e: Exception) {
		e.printStackTrace()
		return null
	}
	return result
}

fun String.dateFormatConvert(oldFormat: String?, newFormat: String?, locale: Locale?): String? {
	var result: String? = null
	
	try {
		if (oldFormat.isNullOrEmpty() || newFormat.isNullOrEmpty()) return null
		
		val oldFormat = SimpleDateFormat(oldFormat,
				locale ?: Locale.KOREA
		)
		val newFormat = SimpleDateFormat(newFormat, locale ?: Locale.KOREA)
		
		val date = oldFormat.parse(this)
		result = newFormat.format(date)
	} catch (e: Exception) {
		e.printStackTrace()
		return null
	}
	return result
}

fun String.getUsernameByEmail(): String {
	if (!this.contains("@")) return this
	return this.substring(0, this.indexOf("@"))
}

fun String.createMultipartBody(field: String): MultipartBody.Part? {
	if (this.isEmpty()) return null
	return MultipartBody.Part.createFormData(field, File(this).name, RequestBody.create(
			MediaType.parse("multipart/form-data"),
			File(this)
	))
}

fun Uri.getImageFilePath(activity: Activity?): String? {
	if (activity == null) {
		Log.e("getImageFilePath", "activity is null")
		return null
	}
	
	var result = ""
	try {
		val proj = arrayOf(MediaStore.Images.Media.DATA)
		val cursor = activity.contentResolver.query(this, proj, null, null, null)
		activity.startManagingCursor(cursor)
		val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
		cursor?.moveToFirst()
		result = cursor?.getString(columnIndex!!)!!
	} catch (e: java.lang.Exception) {e.printStackTrace()}
	return result
}

fun Uri.getVideoFilePath(activity: Activity?): String? {
	if (activity == null) {
		Log.e("getImageFilePath", "activity is null")
		return null
	}
	
	var result = ""
	try {
		val proj = arrayOf(MediaStore.Video.Media.DATA)
		val cursor = activity.contentResolver.query(this, proj, null, null, null)
		activity.startManagingCursor(cursor)
		val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
		cursor?.moveToFirst()
		result = cursor?.getString(columnIndex!!)!!
	} catch (e: java.lang.Exception) {e.printStackTrace()}
	return result
}
