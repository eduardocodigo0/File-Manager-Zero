package com.eduardocodigo0.filemanager.view.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.eduardocodigo0.filemanager.R
import com.eduardocodigo0.filemanager.util.FileType
import com.eduardocodigo0.filemanager.util.getFileTypeFromName
import java.io.File

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FileListItem(
    file: File,
    deleteFile: (File) -> Unit,
    renameFile: (File, String) -> Unit,
    setFileToMove: (File) -> Unit,
    openDirectory: () -> Unit,
    openFile: () -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var openDeleteDialog by remember { mutableStateOf(false) }
    var openRenameDialog by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .combinedClickable(

                onClick = {
                    if (file.isDirectory) {
                        openDirectory()
                    } else {
                        openFile()
                    }
                },
                onLongClick = {
                    expanded = true
                },
            )
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(
                id = if (file.isDirectory) {
                    R.drawable.ic_folder
                } else {
                    when (getFileTypeFromName(file.name)) {

                        FileType.GIF, FileType.IMAGE -> {
                            R.drawable.ic_image
                        }

                        FileType.MSDOC, FileType.MSPPT, FileType.MSXLS, FileType.PDF, FileType.TXT -> {
                            R.drawable.ic_file
                        }

                        FileType.SOUND -> {
                            R.drawable.ic_music
                        }

                        FileType.VIDEO -> {
                            R.drawable.ic_video
                        }

                        else -> {
                            R.drawable.ic_any
                        }
                    }

                }
            ),
            contentDescription = file.name,
            modifier = Modifier
                .padding(2.dp)
                .scale(2F)
        )
        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = file.name,
            modifier = Modifier.padding(2.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(32.dp, 0.dp)
        ) {

            DropdownMenuItem(onClick = {
                expanded = false
                openRenameDialog = true
            }) {
                Text(stringResource(id = R.string.popup_menu_rename))
            }
            Divider()
            DropdownMenuItem(onClick = {
                expanded = false
                openDeleteDialog = true

            }) {
                Text(stringResource(id = R.string.popup_menu_delete))
            }
            if(file.isFile){
                Divider()
                DropdownMenuItem(onClick = {
                    expanded = false
                    setFileToMove(file)
                }) {
                    Text(stringResource(id = R.string.popup_copy_move))
                }
            }

        }
    }
    Divider()


    if (openDeleteDialog) {
        DeleteDialog(dismis = { openDeleteDialog = false }) {
            deleteFile(file)
        }
    }
    if (openRenameDialog) {
        RenameDialog(dismis = { openRenameDialog = false }) {
            renameFile(file, it)
        }
    }
}