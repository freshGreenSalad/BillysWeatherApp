package com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen.individualWeatherComposables.graph

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.models.RadiationByTime

fun firstTransform(
    amountOfPoints : Int,
    HighestRadiation:Int,
    ListOfRadiationDataPoints:List<RadiationByTime>
): List<Offset> {
    return ListOfRadiationDataPoints.map  { RadiationPoint ->
        val radiaiton = ( RadiationPoint.APIValue.toFloat() / HighestRadiation.toFloat()) // TODO: work out how to change tyhis to more significat digits
        val time = (RadiationPoint.time.toFloat() / amountOfPoints.toFloat())
        Offset(x = time, y = radiaiton)
    }
}

fun secondTransform(
    height:Float,
    width: Float,
    ListOfRadiationDataPoints:List<Offset>
): List<Offset> {
    return ListOfRadiationDataPoints.map  { RadiationPoint ->
        Offset(x = RadiationPoint.x*width, y = height - RadiationPoint.y*height)
    }
}



fun ContentDrawScope.drawVerticalLines(offsetPoints: List<Offset>, h: Float){
    offsetPoints.forEach {
        drawLine(Color.Gray, Offset(it.x,0.01f ), Offset(it.x, h))
    }
}

@OptIn(ExperimentalTextApi::class)
fun ContentDrawScope.drawHorisontalLines(list: List<Float>, h: Float, w: Float, textMeasurer: TextMeasurer, relativeMagnitude:Int){

    list.forEachIndexed { index, point ->
        drawText(
            textLayoutResult =textMeasurer.measure(text = ((index+1)*relativeMagnitude).toString()),
            topLeft = Offset(0f, h - point)
        )
        drawLine(color = Color.Gray, Offset(0f, h - point), Offset(w, h - point))
    }
}


fun getHeightsForHorisontalLines(highestRadiationValue:Int, height: Float, relativeMagnitude:Int):List<Float>{

    val amountOfLines = highestRadiationValue/relativeMagnitude
    val heightPerRadiation = height/(highestRadiationValue.toFloat())
    var counter = 1
    val offsetList = mutableListOf<Float>()
    repeat(amountOfLines){
        offsetList.add((counter.toFloat())*relativeMagnitude.toFloat()*heightPerRadiation)
        counter += 1
    }
    return offsetList
}