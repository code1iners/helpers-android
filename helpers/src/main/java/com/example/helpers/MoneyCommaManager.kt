package com.example.helpers

import android.util.Log

class MoneyCommaManager {
	companion object {
		val TAG = MoneyCommaManager::class.simpleName
		
		fun parse(input: String): String {
			Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
			var output = input
			try {
				when (output.length) {
					4 -> { output = output.substring(0, 1) + "," + output.substring(1) }
					5 -> { output = output.substring(0, 2) + "," + output.substring(2) }
					6 -> { output = output.substring(0, 3) + "," + output.substring(3) }
					7 -> { output = output.substring(0, 4) + "," + output.substring(4) }
					8 -> { output = output.substring(0, 5) + "," + output.substring(5) }
					9 -> { output = output.substring(0, 6) + "," + output.substring(6) }
					10 -> { output = output.substring(0, 7) + "," + output.substring(7) }
					11 -> { output = output.substring(0, 8) + "," + output.substring(8) }
				}
			} catch (e: Exception) { e.printStackTrace() }
			
			return output
		}
	}
}