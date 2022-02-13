package com.eduardocodigo0.filemanager.viewmodel

import android.os.Environment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardocodigo0.filemanager.util.StateHolder
import com.eduardocodigo0.filemanager.util.fileCopy
import com.eduardocodigo0.filemanager.util.fileDelete
import com.eduardocodigo0.filemanager.util.fileRename
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.*


class FileListViewModel() : ViewModel() {

    private var currentDirectory = Environment.getExternalStorageDirectory()
    private var directoryStack = mutableListOf<File>(currentDirectory)

    var fileToCopy = mutableStateOf<File?>(null)
        private set

    var deletionState by mutableStateOf<StateHolder>(StateHolder.None())
        private set
    var renameState by mutableStateOf<StateHolder>(StateHolder.None())
        private set
    var copyState by mutableStateOf<StateHolder>(StateHolder.None())
        private set

    var directoryAndFileList by mutableStateOf<List<File>>(listOf())
        private set

    var isSubFolder by mutableStateOf(false)
        private set

    fun getDirectoryAndFileList() {
        val listFiles = currentDirectory.listFiles()
        directoryAndFileList = listFiles.asList().sortedBy { it.name }
        if (directoryStack.last() != currentDirectory) {
            directoryStack.add(currentDirectory)
        }

        isSubFolder = directoryStack.size != 1

    }

    fun changeCurrentDirectory(file: File) {
        if (file.isDirectory) {
            currentDirectory = file
            getDirectoryAndFileList()
        }
    }

    fun returnToPreviousDirectory() {
        if (directoryStack.size > 1) {
            directoryStack.removeLast()
            currentDirectory = directoryStack.last()
            getDirectoryAndFileList()
        }
    }

    fun deleteFile(file: File) {
        viewModelScope.launch(Dispatchers.IO) {
            if(fileDelete(file)){
                deletionState = StateHolder.Success()
            }else{
                deletionState = StateHolder.Fail()
            }
            getDirectoryAndFileList()
        }
    }

    fun setFileToCopy(file: File?) {
        fileToCopy.value = file
    }

    fun copyFile() {
        viewModelScope.launch(Dispatchers.IO) {
            fileToCopy.value?.also { file ->
                if (fileCopy(currentDirectory, file)) {
                    copyState = StateHolder.Success()
                } else {
                    copyState = StateHolder.Fail()
                }
            }
            setFileToCopy(null)
            getDirectoryAndFileList()
        }
    }

    fun renameFile(file: File, newName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (fileRename(file, currentDirectory, newName)) {
                renameState = StateHolder.Success()
            } else {
                renameState = StateHolder.Fail()
            }
            getDirectoryAndFileList()
        }
    }

    fun cleanOperationStates(){
        viewModelScope.launch {
            renameState = StateHolder.None()
            deletionState = StateHolder.None()
            copyState = StateHolder.None()
        }
    }
}