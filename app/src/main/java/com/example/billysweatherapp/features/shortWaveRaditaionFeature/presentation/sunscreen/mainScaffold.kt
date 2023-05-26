package com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.viewModel.RadiationViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,)
@Composable
fun weatherPager(
    viewModel: RadiationViewModel = hiltViewModel()
) {

    val movies = listOf(
        Movie(
            title = "Your Total Sun",
            subtitle = viewModel.totalRadiation.value.toString(),
            0
        ),
        Movie(
            title = "Rain today",
            subtitle = viewModel.totalrain.value.toString(),
            1
        ),
        Movie(
            title = "Temp Today",
            subtitle = viewModel.highestTemp.value.toString(),
            2
        ),
    )

    val scaffoldState = rememberBottomSheetScaffoldState()
    val snackbarState = remember{ SnackbarHostState()}

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 80.dp,
        sheetContent = { sheetContent(movies) },
        snackbarHost = {SnackbarHost(hostState = snackbarState)}
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val horizontalState = rememberPagerState(initialPage = 0)
        //    LaunchedEffect(key1 = true) {
        //        snackbarState.showSnackbar("swipe left to see your other weather metrics")
        //    }
            Column {
                title()
                Divider(Modifier.height(1.dp))
                horizontalPager(horizontalState, Modifier.weight(0.8F),
                    radiation = viewModel.radiation,
                    rain = viewModel.rain,
                    temp =  viewModel.temp, movies)
                Divider(Modifier.height(1.dp))
                verticanamepager(horizontalState, movies,Modifier.weight(0.1F))
            }
        }
    }
}

@Composable
fun title() {
    Text(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = "Billys weather App",
        style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.W300,
            fontSize = 28.sp,
        )
    )
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun sheetContent(apiMetrics: List<Movie>) {
    Column(
        Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("choose the weather stats you wat to see")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            apiMetrics.forEach { title ->
                var selected by remember { mutableStateOf(false) }
                val leadingIcon: @Composable () -> Unit = { Icon(Icons.Default.Check, null) }
                FilterChip(
                    selected,
                    onClick = { selected = !selected },
                    label = { Text(title.title) },
                    leadingIcon = if (selected) leadingIcon else null
                )
            }
        }
    }
}

data class Movie(
    val title: String = "Avengers",
    val subtitle: String = "",
    val index: Int,
)
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.startOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtLeast(0f)
}

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.endOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtMost(0f)
}