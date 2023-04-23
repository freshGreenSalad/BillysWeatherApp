package com.example.billysweatherapp.sunscreen

import androidx.compose.animation.core.Animatable
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
import com.example.billysweatherapp.RadiationByTime

@Composable
fun GraphOfRadiation(radiationByTime:List<RadiationByTime>) {
    val animationProgress = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        animationProgress.animateTo(1f, tween(5000))
    }

    val color = MaterialTheme.colorScheme.primary
    Box(
        Modifier
            .background(color = Color.Transparent)
            .height(300.dp)
            .fillMaxWidth()
            .drawWithCache {
                val highestRadiationValue = findHighestRadiationValue(radiationByTime)
                val h = this.size.height
                val w = this.size.width
                val offsetPoints = ConvertRadiationTimeListToPointsThatFillTheScreen(
                    height = h,
                    width = w,
                    amountOfPoints = radiationByTime.size,
                    HighestRadiation = highestRadiationValue,
                    ListOfRadiationDataPoints = radiationByTime
                )
                val heighstRadiationValueDevidedBy100ConvertedToPoints = heighstRadiationValueDevidedBy100ConvertedToPoints(
                    heighestRadiationValue = highestRadiationValue,
                    height = h)
                val path = cratePathFromRatdiationTimeList(offsetPoints)
                onDrawWithContent {
                    drawContent()
                    heighstRadiationValueDevidedBy100ConvertedToPoints.forEach {
                        drawLine(color = Color.Gray, Offset(0f, h - it), Offset(w, h - it))
                    }
                    drawLine(color = Color.Gray, Offset(0f, h), Offset(w, h))
                    offsetPoints.forEach {
                        drawLine(Color.Gray, Offset(it.x,0.01f ), Offset(it.x, h))
                    }
                    clipRect(right = w * animationProgress.value) {
                        drawPath(
                            path,
                            color,
                            style = Stroke(10.0f, pathEffect = PathEffect.cornerPathEffect(50.0f))
                        )
                    }
                }
            }
    ) {
    }
}

fun heighstRadiationValueDevidedBy100ConvertedToPoints(heighestRadiationValue:Int, height: Float):List<Float>{
    val points = heighestRadiationValue/100
    val heightPerRadiation = height/(heighestRadiationValue.toFloat())
    var counter = 1
    val offsetList = mutableListOf<Float>()
    repeat(points){
        offsetList.add((counter.toFloat())*100f*heightPerRadiation)
        counter += 1
    }
    return offsetList
}

fun cratePathFromRatdiationTimeList(list: List<Offset>): Path {
    val path  = Path()
    path.moveTo(0f,0f)
    for (point in list) {
        path.lineTo(x = point.x,y = point.y)
    }
    return path
}

fun ConvertRadiationTimeListToPointsThatFillTheScreen(
    height:Float,
    width: Float,
    amountOfPoints : Int,
    HighestRadiation:Int,
    ListOfRadiationDataPoints:List<RadiationByTime>
): List<Offset> {
    val offsetPoints = mutableListOf<Offset>()
    for(RadiationDataPoint in ListOfRadiationDataPoints){
        try {
            val radiaiton = (RadiationDataPoint.radiation.toFloat() / HighestRadiation.toFloat()) * height
            val time = (RadiationDataPoint.time.toFloat() / amountOfPoints.toFloat()) * width
            offsetPoints.add(Offset(x = time, y = height - radiaiton))
        }catch (e:Exception){
            println(e)
        }
    }
    return  offsetPoints
}

fun findHighestRadiationValue(radationByTime: List<RadiationByTime>): Int {
    var highestNumber = 0
    for (pair in radationByTime){
        if (pair.radiation > highestNumber){
            highestNumber = pair.radiation
        }
    }
    return highestNumber
}