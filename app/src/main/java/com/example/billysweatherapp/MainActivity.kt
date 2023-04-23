package com.example.billysweatherapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.billysweatherapp.sunscreen.AmountOfRadiationText
import com.example.billysweatherapp.sunscreen.ColumnHoldingRadiationGraphAndTotalRadiationNumber
import com.example.billysweatherapp.sunscreen.sunShader
import com.example.billysweatherapp.ui.theme.BillysWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BillysWeatherAppTheme {
                home()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun home(
    viewModel: RadiationViewModel = hiltViewModel()
) {
    val showGraph = remember { mutableStateOf(false) }
    sunShader()
    ColumnHoldingRadiationGraphAndTotalRadiationNumber(
        radiationList = viewModel.radiation.value,
        totalRadiaiton = viewModel.totalRadiation.value,
        showgraph = showGraph.value
    )
    AmountOfRadiationText(
        getRaditaion = viewModel::getradiation,
        updateShowGraph = {showGraph.value = !showGraph.value}
    )
}



