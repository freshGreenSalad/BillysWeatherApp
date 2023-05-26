package com.example.billysweatherapp.common.presentation

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen.findActivity

@Composable
fun permissionWithoutButton(
    permission: String,
    permissionComposable: @Composable (resetPermissionState:()->Unit)->Unit
) {

    val context = LocalContext.current
    val hasPem = remember {
        mutableStateOf(false)
    }

    val permissionState = remember { mutableStateOf(PermissionState.default) }

    permissionDialog (
        permissionState,
        permissionComposable,
        {permissionState.value = it}
    )

    val permissionCheckResult = ContextCompat.checkSelfPermission(context, permission)

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { permissionGranted ->
            if (permissionGranted) {
                permissionState.value = PermissionState.granted
            } else {
                if (context.findActivity().shouldShowRequestPermissionRationale(permission)) {
                    permissionState.value = PermissionState.denied
                } else {
                    permissionState.value = PermissionState.perminantlydenied
                }
            }
        }

    LaunchedEffect(key1 = hasPem.value){
        launcher.launch(permission)
    }

    if(context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)){
        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED){
            permissionState.value = PermissionState.granted
        } else {
            hasPem.value = true
        }
    } else {
        permissionState.value = PermissionState.hardwearNotAvailable
    }
}

@Composable
fun permissionDialog(
    permissionState: State<PermissionState>,
    requestedPermissionComposable: @Composable (resetPermissionState:()->Unit)->Unit,
    updatepermissonState: (PermissionState) -> Unit
) {
    when (permissionState.value){
        PermissionState.granted -> {
            requestedPermissionComposable { updatepermissonState(PermissionState.default) }
        }
        PermissionState.denied -> {
            deniedAlertDialog(updatepermissonState)
        }
        PermissionState.perminantlydenied -> {
            perminentlyDeniedDialog(updatepermissonState)
        }
        PermissionState.hardwearNotAvailable -> {
            HardwearNotAvailableDialog(updatepermissonState = updatepermissonState)
        }
        PermissionState.default -> {}

    }
}

@Composable
fun HardwearNotAvailableDialog(updatepermissonState: (PermissionState)-> Unit) {
    AlertDialog(
        onDismissRequest = { updatepermissonState(PermissionState.default) },
        dismissButton = {
            TextButton({

            }) {Text("dismiss")}
        },
        confirmButton = {},
        title = {
            Text("Your phone doesn't have a camera")
        },
        text = {
            Text("your phone does not have a camera, this means you wont be able to take photos of your plants")
        }
    )
}

@Composable
fun deniedAlertDialog(updatepermissonState: (PermissionState)-> Unit) {
    AlertDialog(
        onDismissRequest = { updatepermissonState(PermissionState.default) },
        dismissButton = {
            TextButton({ updatepermissonState(PermissionState.default) }
            ) {Text("dismiss")}
        },
        confirmButton = {},
        title = {
            Text("Why you need the camera")
        },
        text = {
            Text("You need the camera permission in Amys' Watering App to take photos of the plants you're watering")
        }
    )
}

@Composable
fun perminentlyDeniedDialog(updatepermissonState: (PermissionState)-> Unit) {
    val context = LocalContext.current
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        dismissButton = {
            TextButton(onClick = { updatepermissonState(PermissionState.default) }
            ){Text("dismiss")}
        },
        confirmButton = {
            TextButton(onClick = {
                updatepermissonState(PermissionState.default)
                context.findActivity().openAppSettings()
            }) {
                Text("Head to App Settings")
            }
        },
        title = {
            Text("Grant permission from settings")
        },
        text = {
            Text("you have perminently denied the camera permission you need to head to your app settings to enable it")
        }
    )
}



enum class PermissionState() {
    hardwearNotAvailable,
    granted,
    denied,
    perminantlydenied,
    default
}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)}

