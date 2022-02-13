package com.eduardocodigo0.filemanager.viewmodel

import android.os.Environment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardocodigo0.filemanager.util.StateHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.File

class FileListViewModel(): ViewModel() {

    private var currentDirectory = Environment.getExternalStorageDirectory()
    private var directoryStack = mutableListOf<File>(currentDirectory)

    var fileToBeMoved = mutableStateOf<File?>(null)
    private set

    var deletionState = MutableStateFlow<StateHolder>(StateHolder.None())
    var renameState= MutableStateFlow<StateHolder>(StateHolder.None())
    var moveState = MutableStateFlow<StateHolder>(StateHolder.None())

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

    fun deleteFile(file: File){
        viewModelScope.launch(Dispatchers.IO){
            try {
                file.delete()
                deletionState.emit(StateHolder.Success())
                getDirectoryAndFileList()
            }catch (err:Exception){
                deletionState.emit(StateHolder.Fail())
            }
            renameState.emit(StateHolder.None())
        }
    }

    fun setFileToBeMoved(file: File?){
        fileToBeMoved.value = file
    }

    fun moveFile(){
        viewModelScope.launch(Dispatchers.IO){
            fileToBeMoved.value?.also { file ->
                try{
                    file.copyTo(currentDirectory)
                    file.delete()
                    moveState.emit(StateHolder.Success())
                    getDirectoryAndFileList()
                    setFileToBeMoved(null)
                }catch (err:Exception){
                    moveState.emit(StateHolder.Fail())
                    setFileToBeMoved(null)
                }
            } ?: run{
                moveState.emit(StateHolder.None())
            }
            renameState.emit(StateHolder.None())
        }
    }

    fun renameFile(file: File, newName: String){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val extension = file.name.split(".").last()
                file.renameTo(File(currentDirectory, "$newName.$extension"))
                renameState.emit(StateHolder.Success())
                getDirectoryAndFileList()
            }catch (err: Exception){
                renameState.emit(StateHolder.Fail())
            }
            renameState.emit(StateHolder.None())
        }
    }

}