package com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun verticanamepager(
    horizontalState: PagerState,
    movies: List<Movie>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val verticalState = rememberPagerState()

        VerticalPager(
            pageCount = movies.size,
            state = verticalState,
            modifier = Modifier
                .height(72.dp),
            userScrollEnabled = false,
            horizontalAlignment = Alignment.Start,
        ) { page ->
            val animateVal = animateIntAsState(
                targetValue = if (horizontalState.currentPage == movies[page].index) movies[page].subtitle.toInt() else 0,
                animationSpec = tween(5000))

            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = movies[page].title,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Thin,
                        fontSize = 28.sp,
                    )
                )
                Text(
                    text = animateVal.value.toString(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.Black.copy(alpha = .56f),
                    )
                )
            }
        }

        LaunchedEffect(Unit) {
            snapshotFlow {
                Pair(
                    horizontalState.currentPage,
                    horizontalState.currentPageOffsetFraction
                )
            }.collect { (page, offset) ->
                verticalState.scrollToPage(page, offset)
            }
        }
    }
}
