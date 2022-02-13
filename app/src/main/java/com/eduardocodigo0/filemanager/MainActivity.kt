package com.eduardocodigo0.filemanager


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eduardocodigo0.filemanager.navigation.Destinations
import com.eduardocodigo0.filemanager.ui.theme.FileManagerZeroTheme
import com.eduardocodigo0.filemanager.view.FileListScreen
import com.eduardocodigo0.filemanager.view.WelcomeScreen
import com.eduardocodigo0.filemanager.util.*


class MainActivity : ComponentActivity() {

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FileManagerZeroTheme {

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent(navController) {
                        requestWriteStoragePermission(this, activityResultLauncher)
                    }
                }
            }
        }
    }
}



@Composable
fun MainContent(navController: NavHostController, requestPermission: () -> Unit) {

    val context = LocalContext.current

    var appBarTitle by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = appBarTitle,
                        color = Color.White
                    )
                },
                backgroundColor = Color.Red
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Destinations.Companion.WelcomeScreen.route
        ) {
            composable(Destinations.Companion.WelcomeScreen.route) {
                appBarTitle = Destinations.Companion.WelcomeScreen.title
                WelcomeScreen() {

                    if (checkPermission(context)) {
                        navController.navigate(Destinations.Companion.FileListScreen.route)
                    } else {
                        requestPermission()
                    }
                }
            }

            composable(Destinations.Companion.FileListScreen.route) {
                appBarTitle = Destinations.Companion.FileListScreen.title
                FileListScreen()
            }
        }
    }
}