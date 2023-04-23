package com.example.billysweatherapp.sunscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AmountOfRadiationText(
    getRaditaion: ()-> Unit,
    updateShowGraph: ()-> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .height(70.dp)
                .clickable {
                    getRaditaion()
                    updateShowGraph()
                },
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .alpha(.5f)
                .background(Color.Gray))
            Text(text = "Get radiation", style = MaterialTheme.typography.headlineMedium)
        }
    }
}