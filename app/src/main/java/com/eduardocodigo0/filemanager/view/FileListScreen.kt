package com.eduardocodigo0.filemanager.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eduardocodigo0.filemanager.R
import com.eduardocodigo0.filemanager.util.FileType
import com.eduardocodigo0.filemanager.util.getFileTypeFromName
import com.eduardocodigo0.filemanager.util.openFile
import com.eduardocodigo0.filemanager.viewmodel.FileListViewModel
import java.io.File

@Composable
fun FileListScreen(viewModel: FileListViewModel = viewModel()){

    val context = LocalContext.current

    viewModel.getDirectoryAndFileList()
    val directoryList = viewModel.directoryAndFileList

    if (directoryList.isNotEmpty()){
        LazyColumn(modifier = Modifier.padding(vertical = 16.dp)){
            items(directoryList){
                FileListItem(it, {
                    viewModel.changeCurrentDirectory(it)
                }){
                    try{
                        openFile(it, context)
                    }catch (err: Exception){
                        Toast.makeText(context, "Cannot open this file", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }else{
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No files found")
        }
    }
    
}



@Composable
fun FileListItem(file: File, openDirectory: () -> Unit, openFile: () -> Unit){
    Row(verticalAlignment = Alignment.CenterVertically
        ,modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable {
                if (file.isDirectory){
                    openDirectory()
                }else{
                    openFile()
                }
            }
    ){
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = if(file.isDirectory){
                R.drawable.ic_folder
            }else{
                when(getFileTypeFromName(file.name)){

                    FileType.GIF, FileType.IMAGE ->{
                        R.drawable.ic_image
                    }

                    FileType.MSDOC, FileType.MSPPT, FileType.MSXLS, FileType.PDF, FileType.TXT ->{
                        R.drawable.ic_file
                    }

                    FileType.SOUND -> {
                        R.drawable.ic_music
                    }

                    FileType.VIDEO -> {
                        R.drawable.ic_video
                    }

                    else ->{
                         R.drawable.ic_any
                    }
                }

            }),
            contentDescription = file.name,
            modifier = Modifier
                .padding(2.dp)
                .scale(2F)
        )
        Spacer(modifier = Modifier.width(16.dp))
        
        Text(
            text = file.name,
            modifier = Modifier.padding(2.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis

        )
    }
    Divider()
}