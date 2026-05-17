package com.example.gandhasiri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gandhasiri.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LegalGuideScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("📜 Legal Guide", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SandalBrown)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .background(CreamBackground)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                "How to Legally Harvest Sandalwood",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                color = SandalBrown
            )

            LegalStep(
                step = "1",
                title = "Register Ownership",
                body = "Register all sandalwood trees on your land with the local Forest Department. This app helps you maintain a digital register."
            )
            LegalStep(
                step = "2",
                title = "Wait for Maturity",
                body = "Sandalwood heartwood takes approximately 15–20 years to form. Only mature trees may be harvested legally."
            )
            LegalStep(
                step = "3",
                title = "Apply for Permission",
                body = "Contact your local Forest Range Officer (FRO) and submit a written application with your tree records and land documents."
            )
            LegalStep(
                step = "4",
                title = "Government Inspection",
                body = "The Forest Department will inspect and certify the trees for harvest. Do not cut any tree before official approval."
            )
            LegalStep(
                step = "5",
                title = "Legal Sale",
                body = "After approval, the sale of sandalwood must go through government-authorized channels. Private sale without permission is illegal."
            )

            Card(
                colors = CardDefaults.cardColors(containerColor = AlertRed.copy(alpha = 0.1f)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text("⚠️ Important Warning", fontWeight = FontWeight.Bold, color = AlertRed)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Illegal cutting or smuggling of sandalwood is a cognizable offence under the Karnataka Forest Act. Penalties include heavy fines and imprisonment.",
                        fontSize = 13.sp,
                        color = BarkDark
                    )
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = LeafGreen.copy(alpha = 0.1f)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text("📞 Contact", fontWeight = FontWeight.Bold, color = LeafGreen)
                    Spacer(Modifier.height(4.dp))
                    Text("Karnataka Forest Department Helpline:", fontSize = 13.sp, color = TextDark)
                    Text("1800-425-1425 (Toll Free)", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = SandalBrown)
                }
            }
        }
    }
}

@Composable
fun LegalStep(step: String, title: String, body: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(modifier = Modifier.padding(14.dp)) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .background(SandalBrown, shape = RoundedCornerShape(50)),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text(step, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold, color = TextDark, fontSize = 14.sp)
                Spacer(Modifier.height(4.dp))
                Text(body, fontSize = 13.sp, color = TextMuted, lineHeight = 18.sp)
            }
        }
    }
}
