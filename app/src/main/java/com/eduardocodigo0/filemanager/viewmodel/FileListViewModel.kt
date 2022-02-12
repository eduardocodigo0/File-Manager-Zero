package com.eduardocodigo0.filemanager.viewmodel

import android.os.Environment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.io.File

class FileListViewModel(): ViewModel() {

    private val root = Environment.getExternalStorageDirectory()

    var directoryAndFileList by mutableStateOf<List<File>>(listOf())
        private set


    fun getDirectoryAndFileList(){
        val listFiles = root.listFiles()
        directoryAndFileList = listFiles.asList()
    }

}