package com.example.helpers

import android.content.Context
import android.util.TypedValue

object MeasureManager {
	// note. measure
	fun Float.toDp(context: Context) : Int = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics)).toInt()
	fun Int.toDp(context: Context) : Int = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics)).toInt()
}