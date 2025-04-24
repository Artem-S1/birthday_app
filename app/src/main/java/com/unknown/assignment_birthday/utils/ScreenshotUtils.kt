package com.unknown.assignment_birthday.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun captureScreenshot(context: Context): Bitmap {
    val rootView = (context as? Activity)?.window?.decorView?.rootView
        ?: throw IllegalStateException("Context should be an instance of Activity")

    val bitmap = Bitmap.createBitmap(rootView.width, rootView.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    rootView.draw(canvas)

    return bitmap
}


fun saveScreenshot(context: Context, bitmap: Bitmap) {
    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "screenshots.png")
    try {
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}