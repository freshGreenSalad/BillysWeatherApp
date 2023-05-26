package com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen.individualWeatherComposables.graph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalTextApi::class)
@Composable
fun GraphGrid(
    listOfPoints:List<Offset>,
    highestRadiationValue: Int,
    relativeMagnitude:Int
) {
    val textMeasurer = rememberTextMeasurer()
    Box(
        Modifier
            .background(color = Color.Transparent)
            .height(300.dp)
            .fillMaxWidth()
            .drawWithCache {

                val h = this.size.height
                val w = this.size.width

                val offsetPoints = secondTransform(h,w,listOfPoints)

                val listOfHights = getHeightsForHorisontalLines(
                    highestRadiationValue = highestRadiationValue,
                    height = h,
                    relativeMagnitude
                )

                onDrawWithContent {
                    drawContent()
                    drawHorisontalLines(listOfHights, h, w, textMeasurer, relativeMagnitude)
                    drawLine(color = Color.Gray, Offset(0f, h), Offset(w, h))
                    drawVerticalLines(offsetPoints, h)
                }
            }
    ){}
}

