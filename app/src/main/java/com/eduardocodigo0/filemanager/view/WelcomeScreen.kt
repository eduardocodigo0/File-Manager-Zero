package com.eduardocodigo0.filemanager.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.eduardocodigo0.filemanager.R
import com.eduardocodigo0.filemanager.ui.theme.FileManagerZeroTheme
import org.intellij.lang.annotations.JdkConstants


@Composable
fun WelcomeScreen(navHostController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Wellcome to:",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,

        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "File Manager",
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        Image(painterResource(id = R.drawable.logo), "Logo")


        Spacer(modifier = Modifier.height(64.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Open Files")
        }
    }
}



@Preview
@Composable
fun WelcomeScreenPreview(){
    val navHostController = rememberNavController()
    WelcomeScreen(navHostController)
}
