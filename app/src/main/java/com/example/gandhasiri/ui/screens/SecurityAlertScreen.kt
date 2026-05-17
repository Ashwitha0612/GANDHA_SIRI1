package com.example.gandhasiri.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gandhasiri.ui.theme.*
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityAlertScreen(onBack: () -> Unit) {
    var alertSent by remember { mutableStateOf(false) }
    var countdown by remember { mutableStateOf(0) }
    var alertLog by remember { mutableStateOf(listOf<String>()) }

    // Pulsing animation for the panic button
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.08f,
        animationSpec = infiniteRepeatable(tween(700), RepeatMode.Reverse),
        label = "scale"
    )

    LaunchedEffect(alertSent) {
        if (alertSent) {
            countdown = 3
            while (countdown > 0) { delay(1000); countdown-- }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🚨 Security Alert", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AlertRed)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .background(CreamBackground)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier.height(8.dp))

            Text(
                "Press the PANIC BUTTON if you notice suspicious activity near your sandalwood trees.",
                textAlign = TextAlign.Center,
                color = TextMuted,
                fontSize = 14.sp
            )

            // Panic Button
            Button(
                onClick = {
                    alertSent = true
                    val timestamp = java.text.SimpleDateFormat("hh:mm a, dd MMM", java.util.Locale.getDefault())
                        .format(java.util.Date())
                    alertLog = alertLog + "🔴 Alert sent at $timestamp"
                },
                modifier = Modifier
                    .size(180.dp)
                    .scale(scale),
                shape = RoundedCornerShape(90.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AlertRed),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("🚨", fontSize = 40.sp)
                    Text("PANIC", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                    Text("BUTTON", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }

            if (alertSent) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = SafeGreen),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("✅ Alert Sent!", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                        Spacer(Modifier.height(6.dp))
                        Text("[SIMULATED] Notification sent to neighbors and Forest Guard.", color = Color.White, fontSize = 13.sp, textAlign = TextAlign.Center)
                        Text("[SIMULATED] SMS: 'Suspicious activity near my sandalwood trees. Please check immediately!'", color = Color.White.copy(0.85f), fontSize = 12.sp, textAlign = TextAlign.Center)
                    }
                }
            }

            // What to do
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("📋 What to do if you suspect theft:", fontWeight = FontWeight.Bold, color = TextDark, fontSize = 14.sp)
                    Spacer(Modifier.height(10.dp))
                    AlertStep("1. Do NOT confront suspects alone.")
                    AlertStep("2. Call 100 (Police) immediately.")
                    AlertStep("3. Call Forest Dept: 1800-425-1425")
                    AlertStep("4. Note descriptions: time, persons, vehicles.")
                    AlertStep("5. Use this app to record the incident.")
                }
            }

            // Alert log
            if (alertLog.isNotEmpty()) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text("Alert History", fontWeight = FontWeight.Bold, color = TextDark, fontSize = 14.sp)
                        Spacer(Modifier.height(8.dp))
                        alertLog.forEach { log ->
                            Text(log, fontSize = 12.sp, color = TextMuted)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AlertStep(text: String) {
    Text(text, fontSize = 13.sp, color = TextMuted, modifier = Modifier.padding(vertical = 2.dp))
}
