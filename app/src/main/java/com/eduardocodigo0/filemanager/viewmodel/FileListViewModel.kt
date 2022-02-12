package com.eduardocodigo0.filemanager.viewmodel

import android.os.Environment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.io.File

class FileListViewModel(): ViewModel() {

    private var currentDirectory = Environment.getExternalStorageDirectory()
    private var directoryStack = mutableListOf<File>(currentDirectory)


    var directoryAndFileList by mutableStateOf<List<File>>(listOf())
        private set

    var isSubFolder by mutableStateOf(false)

    fun getDirectoryAndFileList(){
        val listFiles = currentDirectory.listFiles()
        directoryAndFileList = listFiles.asList()
        if(directoryStack.last() != currentDirectory){
            directoryStack.add(currentDirectory)
        }

        isSubFolder = directoryStack.size != 1

    }

    fun changeCurrentDirectory(file: File){
        if (file.isDirectory){
            currentDirectory = file
            getDirectoryAndFileList()
        }
    }

    fun returnToPreviousDirectory(){
        if(directoryStack.size > 1){
            directoryStack.removeLast()
            currentDirectory = directoryStack.last()
            getDirectoryAndFileList()
        }
    }
}