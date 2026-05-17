package com.example.gandhasiri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gandhasiri.ui.theme.SandalBrown
import com.example.gandhasiri.ui.theme.GoldAccent
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(2500)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "🌳", fontSize = 90.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Gandha-Siri",
                style = MaterialTheme.typography.displaySmall.copy(
                    color = SandalBrown,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 2.sp
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Sandalwood Farmer's Guard",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = GoldAccent,
                    letterSpacing = 1.sp
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "ಗಂಧ-ಸಿರಿ • Digital Tree Register",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )
        }
    }
}
