package com.eduardocodigo0.filemanager.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FileListScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {

    }
}






@Preview
@Composable
fun FileListPreview(){
    FileListScreen()
}