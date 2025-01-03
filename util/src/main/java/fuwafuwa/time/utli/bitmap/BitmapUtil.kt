package fuwafuwa.time.utli.bitmap

import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun Bitmap.saveAsPngToFile(outputFile: File) {
    try {
        val outputStream = FileOutputStream(outputFile)
        this.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}
