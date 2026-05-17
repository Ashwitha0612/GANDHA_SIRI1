package com.example.gandhasiri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gandhasiri.ui.theme.*
import com.example.gandhasiri.viewmodel.TreeViewModel

@Composable
fun HomeScreen(
    viewModel: TreeViewModel,
    onRegisterTree: () -> Unit,
    onViewTrees: () -> Unit,
    onLegalGuide: () -> Unit,
    onSecurityAlert: () -> Unit
) {
    val count by viewModel.treeCount.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CreamBackground)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SandalBrown)
                .padding(24.dp)
        ) {
            Column {
                Text(
                    text = "🌳 Gandha-Siri",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Sandalwood Farmer's Guard",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 13.sp
                )
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {

            // Stats Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("🪵", fontSize = 40.sp)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "$count",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = SandalBrown
                        )
                        Text(
                            text = "Trees Registered",
                            color = TextMuted,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Quick Actions",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = TextDark
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Action Grid
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                HomeActionCard(
                    modifier = Modifier.weight(1f),
                    emoji = "🌱",
                    title = "Register Tree",
                    subtitle = "Tag a new tree",
                    bgColor = SandalBrown,
                    onClick = onRegisterTree
                )
                HomeActionCard(
                    modifier = Modifier.weight(1f),
                    emoji = "📋",
                    title = "My Trees",
                    subtitle = "View all records",
                    bgColor = WoodLight,
                    onClick = onViewTrees
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                HomeActionCard(
                    modifier = Modifier.weight(1f),
                    emoji = "📜",
                    title = "Legal Guide",
                    subtitle = "Harvesting laws",
                    bgColor = LeafGreen,
                    onClick = onLegalGuide
                )
                HomeActionCard(
                    modifier = Modifier.weight(1f),
                    emoji = "🚨",
                    title = "Security Alert",
                    subtitle = "Panic button",
                    bgColor = AlertRed,
                    onClick = onSecurityAlert
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Info banner
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = GoldAccent.copy(alpha = 0.15f)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("💡", fontSize = 22.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Register each sandalwood tree with its GPS location, girth & photo to create a legally traceable digital record.",
                        fontSize = 12.sp,
                        color = BarkDark,
                        lineHeight = 17.sp
                    )
                }
            }
        }
    }
}

@Composable
fun HomeActionCard(
    modifier: Modifier = Modifier,
    emoji: String,
    title: String,
    subtitle: String,
    bgColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column {
            Text(emoji, fontSize = 32.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(subtitle, color = Color.White.copy(alpha = 0.8f), fontSize = 11.sp)
        }
    }
}
