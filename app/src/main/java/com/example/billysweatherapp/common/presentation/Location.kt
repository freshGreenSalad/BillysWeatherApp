package com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.location.Location
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.billysweatherapp.common.presentation.permissionWithoutButton
import com.google.android.gms.location.LocationServices


@Composable
fun LocationComp() {
    permissionWithoutButton(
        permission = Manifest.permission.ACCESS_COARSE_LOCATION,
        permissionComposable = {corseLocationProvider()}
    )
}



@SuppressLint("MissingPermission")
@Composable
fun corseLocationProvider() {
    val locationText  = remember { mutableStateOf<Location?>(null) }
    Text(locationText.value.toString())
    val context = LocalContext.current
    val activity = context.findActivity()
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

    val location =  fusedLocationClient.getCurrentLocation(3, null)
}

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}
