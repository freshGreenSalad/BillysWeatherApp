package com.example.billysweatherapp.sunscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.example.billysweatherapp.RadiationByTime

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ColumnHoldingRadiationGraphAndTotalRadiationNumber(
    radiationList: List<RadiationByTime>,
    totalRadiaiton: Int,
    showgraph: Boolean
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        if (showgraph) { GraphOfRadiation(radiationList) }
        if(showgraph) {
            TextShowingTotalSun(
                totalRadiaiton = totalRadiaiton,
                showgraph = showgraph
            )
        }
    }
}

@Composable
fun TextShowingTotalSun(
    totalRadiaiton:Int,
    showgraph:Boolean
) {
    Row {
        Spacer(modifier = Modifier.width(100.dp))
        val radiation: Int by animateIntAsState(targetValue = if (!showgraph) 0 else totalRadiaiton, animationSpec = tween(2000))
        Text(
            modifier = Modifier.alpha(.75f),
            text = "Your total daily Sun  ",
            style = MaterialTheme.typography.titleLarge)
        Text(
            modifier = Modifier.alpha(.75f),
            text = radiation.toString(),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}


