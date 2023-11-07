package com.br.streamcontrol.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.ByteArrayOutputStream
import java.io.IOException

fun bitmapToUri(context: Context, bitmap: Bitmap): Uri? {
        var uri: Uri? = null
        val bytes = ByteArrayOutputStream()

        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val imagePath: String = MediaStore.Images.Media.insertImage(
                context.contentResolver,
                bitmap,
                "temp_image",
                null
            )
            uri = Uri.parse(imagePath)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                bytes.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return uri
    }
