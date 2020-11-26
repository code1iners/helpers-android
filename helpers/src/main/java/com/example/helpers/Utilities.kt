package com.example.helpers

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
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