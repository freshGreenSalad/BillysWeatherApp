package com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.domain.models.RadiationByTime
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen.individualWeatherComposables.RadiationMainScreen
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen.individualWeatherComposables.RainMainScreen
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen.individualWeatherComposables.TempMainScreen

/*fun sfd():Float{
    {
        if ( && !justOpenedApp) {
            1f
        } else 0f
    }}
    return 0f}*/


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun horizontalPager(
    horizontalState: PagerState, modifier:
    Modifier = Modifier,
    radiation : MutableState<List<RadiationByTime>>,
    rain : MutableState<List<RadiationByTime>>,
    temp : MutableState<List<RadiationByTime>>,
    movies: List<Movie>
) {
    HorizontalPager(
        pageCount = 3,
        modifier = modifier
            .padding(
                top = 32.dp
            ),
        state = horizontalState,
        pageSpacing = 1.dp,
        beyondBoundsPageCount = 9,
    ) { page ->

        var justOpenedApp = true

        var targetval = 0f

        LaunchedEffect(key1 = true){
            if (horizontalState.currentPage == movies[page].index ){
                targetval = 1f
            } else {
                0f
            }
        }


        val animateVal = animateFloatAsState(
            targetValue = if (horizontalState.currentPage == movies[page].index){1f}else{0f},
            animationSpec = tween(5000),
            finishedListener = {justOpenedApp = false}
        )

        Box(
            modifier = Modifier
                .zIndex(page * 10f)
                .padding(
                    start = 64.dp,
                    end = 32.dp,
                )
                .graphicsLayer {
                    val startOffset = horizontalState.startOffsetForPage(page)
                    translationX = size.width * (startOffset * .99f)
                    alpha = (2f - startOffset) / 2f
                    val blur = (startOffset * 20f).coerceAtLeast(0.1f)
                    renderEffect = RenderEffect
                        .createBlurEffect(
                            blur, blur, Shader.TileMode.DECAL
                        )
                        .asComposeRenderEffect()
                    val scale = 1f - (startOffset * .1f)
                    scaleX = scale
                    scaleY = scale
                }
                .clip(RoundedCornerShape(20.dp))
                .background(
                    color = Color(0xFFF58133),
                    shape = RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            when (page) {
                0 -> {
                    RadiationMainScreen(radiation,animateVal)
                }
                1 -> {
                    RainMainScreen(rain,animateVal)
                }
                2 -> {
                    TempMainScreen(temp,animateVal)
                }
            }
        }
    }
}
