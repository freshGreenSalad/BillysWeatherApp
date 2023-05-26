package com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen.individualWeatherComposables.graph

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.dp
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.models.RadiationByTime
import kotlin.math.ceil

@Composable
fun GraphOfRadiation(radiationByTime:List<RadiationByTime>,animateVal: State<Float>) {

    val relativeMagnitude = 100

    val highestRadiationValue = radiationByTime.maxOfOrNull{ceil( (it.APIValue.toFloat()/ relativeMagnitude.toFloat()))}
        ?.toInt()?.times(relativeMagnitude)
        ?: 0
    val listOfPoints = firstTransform(radiationByTime.size-1, highestRadiationValue,radiationByTime)
    Log.d("radiaiton points", listOfPoints.toString())
Box() {
    GraphGrid(listOfPoints, highestRadiationValue,relativeMagnitude)
    RadiationLine(listOfPoints, animateVal)
}}

@Composable
fun RadiationLine(
    listOfPoints: List<Offset>,
    animateVal: State<Float>
) {
    val color = MaterialTheme.colorScheme.primary

    Box(
        Modifier
            .background(color = Color.Transparent)
            .height(300.dp)
            .fillMaxWidth()
            .drawWithCache {

                val h = this.size.height
                val w = this.size.width

                val offsetPoints = secondTransform(h,w,listOfPoints)
                val path = cratePathFromRatdiationTimeList(offsetPoints)

                onDrawWithContent {
                    drawContent()

                    clipRect(right = w * animateVal.value) {
                        drawPath(
                            path, color,
                            style = Stroke(10.0f, pathEffect = PathEffect.cornerPathEffect(50.0f))
                        )
                    }
                }
            }
    ) {}
}



fun cratePathFromRatdiationTimeList(list: List<Offset>): Path {
    val path  = Path()
    if (list.isNotEmpty()) {
        path.moveTo(list[0].x, list[0].y)
    }
    for (point in list) {
        path.lineTo(x = point.x,y = point.y)
    }
    return path
}
