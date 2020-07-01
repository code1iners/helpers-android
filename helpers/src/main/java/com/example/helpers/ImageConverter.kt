package com.example.helpers

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class ImageConverter {
    companion object { private lateinit var loader: ProgressDialog }
    private val TAG = ImageConverter::class.simpleName
    fun flieToBitmap(file: File): Bitmap? {
        Log.w(TAG, object: Any(){}.javaClass.enclosingMethod!!.name)
        return BitmapFactory.decodeFile(file.path)
    }

    fun bitmapToFile(bitmap: Bitmap, destPath: String, fileName: String): String? {
        Log.w(TAG, object: Any(){}.javaClass.enclosingMethod!!.name)
        val f = File(destPath, fileName)
        if (!File(destPath).exists()) File(destPath).mkdirs()
        f.createNewFile()
        // conver bitmap to byte array
        val bitmap = bitmap
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
        val bitmapData = bos.toByteArray()
        // write the bytes in file
        val fos = FileOutputStream(f)
        fos.write(bitmapData)
        fos.flush()
        fos.close()
        Log.i(TAG, "result : ${destPath + File.separator + fileName}")
        return destPath + File.separator +fileName
    }
    
    fun drawableToBitmap(context: Context, drawable: Int): Bitmap {
        Log.w(TAG, object: Any(){}.javaClass.enclosingMethod!!.name)
        return BitmapFactory.decodeResource(context.resources, drawable)
    }
    
    fun drawableToFile(context: Context, drawable: Int, dirName: String, fileName: String): String? {
        Log.w(TAG, object: Any(){}.javaClass.enclosingMethod!!.name)
        val bitmap = BitmapFactory.decodeResource(context.resources, drawable)
        return bitmapToFile(bitmap, dirName, fileName)
    }
}