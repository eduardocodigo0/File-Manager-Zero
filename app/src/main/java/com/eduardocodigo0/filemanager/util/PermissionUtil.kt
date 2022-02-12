package com.eduardocodigo0.filemanager.util

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.eduardocodigo0.filemanager.R

fun checkPermission(context: Context): Boolean {

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        Environment.isExternalStorageManager()
    } else {
        val permission =
            ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        permission == PackageManager.PERMISSION_GRANTED
    }
}

fun requestWriteStoragePermission(
    activity: Activity,
    activityResultLauncher: ActivityResultLauncher<Intent>
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

        createPermissionAlertDialog(activity) {
            val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
            activityResultLauncher.launch(intent)
        }.show()

    } else {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            Toast.makeText(
                activity,
                activity.getText(R.string.permission_denied_text),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            ActivityCompat.requestPermissions(
                activity, arrayOf<String>(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 111
            )
        }
    }
}

private fun createPermissionAlertDialog(activity: Activity, openSettings: (Activity) -> Unit) =
    activity.let {
        val builder = AlertDialog.Builder(it)
        builder.setMessage(R.string.permission_dialog_text)
            .setPositiveButton(R.string.open_setings) { dialog, id ->
                openSettings(activity)
            }
            .setNegativeButton(R.string.cancel) { _, _ ->
            }

        builder.create()
    }


