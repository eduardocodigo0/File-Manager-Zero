package com.eduardocodigo0.filemanager.view

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eduardocodigo0.filemanager.R
import com.eduardocodigo0.filemanager.viewmodel.FileListViewModel

@Composable
fun FileListScreen(viewModel: FileListViewModel = viewModel()){

    viewModel.getDirectoryAndFileList()
    val directoryList = viewModel.directoryAndFileList

    if (directoryList.isNotEmpty()){
        LazyColumn(modifier = Modifier.padding(vertical = 16.dp)){
            items(directoryList){
                FileListItem(name = it.name, isDirectory = it.isDirectory)
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
fun FileListItem(name: String, isDirectory: Boolean){
    Row(verticalAlignment = Alignment.CenterVertically
        ,modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable {

            }
    ){
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = if(isDirectory){
                R.drawable.ic_folder
            }else{
                R.drawable.ic_file
            }),
            contentDescription = name,
            modifier = Modifier
                .padding(2.dp)
                .scale(2F)
        )
        Spacer(modifier = Modifier.width(16.dp))
        
        Text(
            text = name,
            modifier = Modifier.padding(2.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis

        )
    }
    Divider()
}

@Preview(showBackground = true)
@Composable
fun FileListPreview(){
    FileListItem("Downloads", false)
}