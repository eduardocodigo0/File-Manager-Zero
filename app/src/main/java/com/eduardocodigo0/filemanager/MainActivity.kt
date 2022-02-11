package com.eduardocodigo0.filemanager

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eduardocodigo0.filemanager.navigation.Destinations
import com.eduardocodigo0.filemanager.ui.theme.FileManagerZeroTheme
import com.eduardocodigo0.filemanager.view.ErrorScreen
import com.eduardocodigo0.filemanager.view.FileListScreen
import com.eduardocodigo0.filemanager.view.WelcomeScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContent {
            FileManagerZeroTheme {

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent(navController)
                }
            }
        }
    }
}

private fun checkPermission(activity: Activity): Boolean{
    val permission = ContextCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE)
    return permission == PackageManager.PERMISSION_GRANTED
}

private fun requestWriteStoragePermission(activity: Activity){

    if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        Toast.makeText(activity, "This app requires the storage permission, please allow from settings", Toast.LENGTH_SHORT).show()
    }else{
        ActivityCompat.requestPermissions(activity, arrayOf<String>(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
        ), 111)
    }

}

@Composable
fun MainContent(navController: NavHostController) {
    Scaffold() {
        NavHost(navController = navController, startDestination = Destinations.Companion.WelcomeScreen.route){
            composable(Destinations.Companion.WelcomeScreen.route){
               WelcomeScreen(navHostController = navController)
            }

            composable(Destinations.Companion.ErrorScreen.route){
                ErrorScreen()
            }

            composable(Destinations.Companion.FileListScreen.route){
                FileListScreen()
            }
        }
    }
}