package com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen.individualWeatherComposables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.models.RadiationByTime
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen.RainShader
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen.individualWeatherComposables.graph.GraphOfRadiation
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen.individualWeatherComposables.graph.GraphOfTemp
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen.sunShader
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen.tempShader

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun RadiationMainScreen(
    radiation : MutableState<List<RadiationByTime>>,
    animateVal: State<Float>
) {
    sunShader()
    GraphOfRadiation(radiation.value,animateVal)
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun RainMainScreen(
    rain : MutableState<List<RadiationByTime>>,
    animateVal: State<Float>
) {
    RainShader()
    GraphOfRadiation(rain.value,animateVal)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun TempMainScreen(
    temp : MutableState<List<RadiationByTime>>,
    animateVal: State<Float>
) {
    tempShader()
    GraphOfTemp(temp.value,animateVal)
}