package com.eduardocodigo0.filemanager.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eduardocodigo0.filemanager.R

@Composable
fun CopyFileSnack(dismis: () -> Unit, moveFile: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color.Black)
            .padding(8.dp),
    ) {
        Text(
            text = stringResource(id = R.string.copy_snack_text),
            color = Color.White,
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextButton(
                onClick = {
                    moveFile()
                }) {
                Text(
                    text = stringResource(id = R.string.copy_snack_here),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            TextButton(onClick = {
                dismis()
            }) {
                Text(
                    text = stringResource(id = R.string.copy_snack_cancel),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}