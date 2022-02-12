package com.eduardocodigo0.filemanager.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.io.File

enum class FileType(val value: String){

    IMAGE("image/jpeg"),
    GIF("image/gif"),
    SOUND("audio/x-wav"),
    TXT("text/plain"),
    MSDOC("application/msword"),
    PDF("application/pdf"),
    MSXLS("application/vnd.ms-excel"),
    MSPPT("application/vnd.ms-powerpoint"),
    VIDEO("video/*"),
    ANY("*/*")

}


fun openFile(file: File, context: Context){
    val intent = Intent()
        .setAction(Intent.ACTION_VIEW)
        .setDataAndType(Uri.parse(file.canonicalPath), getFileTypeFromName(file.name).value)
        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    context.startActivity(intent)
}

fun getFileTypeFromName(url: String): FileType{
    return if (url.contains(".doc") || url.contains(".docx")) {
        FileType.MSDOC
    } else if(url.contains(".pdf")) {
        FileType.PDF
    } else if(url.contains(".ppt") || url.contains(".pptx")) {
       FileType.MSPPT
    } else if(url.contains(".xls") || url.contains(".xlsx")) {
        FileType.MSXLS
    } else if(url.contains(".wav") || url.contains(".mp3")) {
        FileType.SOUND
    } else if(url.contains(".gif")) {
        FileType.GIF
    } else if(url.contains(".jpg") || url.contains(".jpeg") || url.contains(".png")) {
        FileType.IMAGE
    } else if(url.contains(".txt")) {
        FileType.TXT
    } else if(url.contains(".3gp") || url.contains(".mpg") || url.contains(".mpeg") || url.contains(".mpe") || url.contains(".mp4") || url.contains(".avi")) {
        FileType.VIDEO
    } else {
        FileType.ANY
    }
}