package com.eduardocodigo0.filemanager.view.components

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.eduardocodigo0.filemanager.R

@Composable
fun DeleteDialog(dismis: () -> Unit, action: () -> Unit) {
    AlertDialog(
        onDismissRequest = {
            dismis()
        },
        title = {
            Text(text = stringResource(id = R.string.delete_dialog_caption))
        },
        text = {
            Text(
                stringResource(id = R.string.delete_dialog_msg)
            )
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Red
                ),
                onClick = {
                    dismis()
                    action()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.yes),
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Red
                ),
                onClick = {
                    dismis()
                },
            ) {
                Text(
                    text = stringResource(id = R.string.no),
                    color = Color.White
                )
            }
        }
    )
}

