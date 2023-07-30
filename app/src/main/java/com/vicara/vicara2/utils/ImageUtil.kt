package com.vicara.vicara2.utils

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import com.vicara.vicara2.R
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Locale

private const val FILENAME_FORMAT = "dd-MMM-yyyy-H-m-S"

fun pathToBitmap(path: String, context: Context) : Bitmap{
    val file = File(path)

    val imgBitmap: Bitmap = if (file.exists()) {
        BitmapFactory.decodeFile(file.absolutePath)
    } else {
        BitmapFactory.decodeResource(context.resources, R.drawable.gallery)
    }

    return imgBitmap
}

fun isFileExist(path: String) : Boolean {
    val file = File(path)
    if (file.exists()){
        return true
    }
    return false
}

fun createFile(application: Application): File {
    val mediaDir = application.externalMediaDirs.firstOrNull()?.let {
        File(it, application.resources.getString(R.string.app_name)).apply { mkdirs() }
    }

    val outputDirectory = if (
        mediaDir != null && mediaDir.exists()
    ) mediaDir else application.filesDir

    val timeStamp: String = SimpleDateFormat(
        FILENAME_FORMAT,
        Locale.US
    ).format(System.currentTimeMillis())
    val result = File(outputDirectory, "$timeStamp.jpg")
    if (result.exists()){
        result.delete()
    }
    return result
}

fun saveFile(application: Application, file: File){
    val mediaDir = application.externalMediaDirs.firstOrNull()?.let {
        File(it, application.resources.getString(R.string.app_name)).apply { mkdirs() }
    }

    val outputDirectory = if (
        mediaDir != null && mediaDir.exists()
    ) mediaDir else application.filesDir

    val timeStamp: String = SimpleDateFormat(
        FILENAME_FORMAT,
        Locale.US
    ).format(System.currentTimeMillis())

    val result = File(outputDirectory, "$timeStamp.jpg")
    file.copyTo(result, overwrite = true)
}

fun createTempFile(context: Context): File {
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val timeStamp: String = SimpleDateFormat(
        FILENAME_FORMAT,
        Locale.US
    ).format(System.currentTimeMillis())
    return File.createTempFile(timeStamp, ".jpg", storageDir)
}

fun uriToFile(selecedImg: Uri, application: Application, context: Context) : File{
    val contentResolver: ContentResolver = context.contentResolver
    val myFile = createFile(application)

    val inputStream = contentResolver.openInputStream(selecedImg) as InputStream
    val outputStream : OutputStream = FileOutputStream(myFile)
    val buf = ByteArray(1024)
    var len: Int
    while (inputStream.read(buf).also{len = it} > 0) outputStream.write(buf, 0, len)
    outputStream.close()
    inputStream.close()

    return myFile
}

