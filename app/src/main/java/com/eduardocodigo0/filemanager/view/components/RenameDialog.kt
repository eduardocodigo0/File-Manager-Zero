package com.eduardocodigo0.filemanager.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eduardocodigo0.filemanager.R

@Composable
fun RenameDialog(dismis: () -> Unit, action: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = {
            dismis()
        },
        title = {
            Text(text = stringResource(id = R.string.rename_dialog_caption))
        },
        text = {
            TextField(
                value = text,
                onValueChange = {

                    text = it.take(16)
                        .trim()
                        .filter { it.isLetter() || it.isDigit() }

                },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Red,
                    focusedIndicatorColor = Color.Red
                ),
                maxLines = 1,

                )
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Red
                ),
                onClick = {
                    dismis()
                    action(text)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.rename),
                    color = Color.White
                )
            }
        },
    )
}