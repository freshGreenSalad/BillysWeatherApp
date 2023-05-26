package com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.data.RadiationRepository
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.models.RadiationByTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RadiationViewModel @Inject constructor(
    private val radiationRepository: RadiationRepository
    ): ViewModel() {
    private val scope = viewModelScope

    val radiation = mutableStateOf(listOf<RadiationByTime>())
    val rain = mutableStateOf(listOf<RadiationByTime>())
    val temp = mutableStateOf(listOf<RadiationByTime>())

    val totalRadiation = mutableStateOf(0)
    val totalrain = mutableStateOf(0)
    val highestTemp = mutableStateOf(0)
    init {
        getradiation()
        getRain()
        getTemp()
    }

    fun getradiation(){
        scope.launch(Dispatchers.IO){
            val radiationValue = radiationRepository.getListOfRadiationByTimePoints()
            radiation.value = radiationValue
            totalRadiation.value = getTotalRadiation(radiationValue)
        }
    }
    fun getRain(){
        scope.launch(Dispatchers.IO){
            val rainValue = radiationRepository.getListOfPrecipitationByTimePoints()
            rain.value = rainValue
            totalrain.value = getTotalRadiation(rainValue)
        }
    }
    fun getTemp(){
        scope.launch(Dispatchers.IO){
            val temper = radiationRepository.getListOfAirTempByTimePoints()
            temp.value = temper.map{ RadiationByTime( it.time,it.APIValue-273)}
            Log.d("temp in K", temp.value.toString())
            highestTemp.value = temper.maxOfOrNull { it.APIValue - 273 }?:0
        }
    }
}
private fun getTotalRadiation(radiationValue:List<RadiationByTime>): Int{
    var total = 0
    radiationValue.forEach {total += it.APIValue}
    return total
}