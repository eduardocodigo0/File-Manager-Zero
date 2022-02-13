package com.eduardocodigo0.filemanager.navigation

sealed class Destinations(val route: String, val title: String) {
    companion object{
        object FileListScreen:Destinations("file_list_screen", "Files")
        object WelcomeScreen:Destinations("welcome_screen", "File Manager Zero")
    }
}