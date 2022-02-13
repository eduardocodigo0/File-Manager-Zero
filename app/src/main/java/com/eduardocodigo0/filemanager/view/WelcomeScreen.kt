package com.eduardocodigo0.filemanager.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.eduardocodigo0.filemanager.R


@Composable
fun WelcomeScreen(openFilesBtnAction: () -> Unit){

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .verticalScroll(scrollState)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = stringResource(id = R.string.welcome_to),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,

        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.file_manager),
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        Image(painterResource(id = R.drawable.logo), stringResource(id = R.string.logo_content_descriprion),
            modifier = Modifier.padding(32.dp)
            )


        Spacer(modifier = Modifier.height(64.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { openFilesBtnAction() },
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(
                text = stringResource(id = R.string.open_files_button_text),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp

            )
        }
    }
}



@Preview
@Composable
fun WelcomeScreenPreview(){
    val navHostController = rememberNavController()
    WelcomeScreen(){

    }
}
