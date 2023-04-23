package com.example.billysweatherapp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RadiationViewModel @Inject constructor(
    private val radiationRepository: RadiationRepository
    ): ViewModel() {
        val scope = viewModelScope

    val radiation = mutableStateOf(listOf<RadiationByTime>())

    val totalRadiation = mutableStateOf(0)


    fun getradiation(){
        scope.launch(Dispatchers.IO){
            val radiationValue = radiationRepository.getRadiation()
            radiation.value = radiationValue
            totalRadiation.value = getTotalRadiation(radiationValue)
        }
    }
}
private fun getTotalRadiation(radiationValue:List<RadiationByTime>): Int{
    var total = 0
    radiationValue.forEach {total += it.radiation
    }
    Log.d("total sunshine" ,total.toString())
    return total
}