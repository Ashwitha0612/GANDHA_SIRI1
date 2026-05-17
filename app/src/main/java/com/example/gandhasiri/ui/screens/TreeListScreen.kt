package com.example.gandhasiri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gandhasiri.data.Tree
import com.example.gandhasiri.ui.theme.*
import com.example.gandhasiri.viewmodel.TreeViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreeListScreen(
    viewModel: TreeViewModel,
    onTreeClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    val trees by viewModel.trees.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Sandalwood Trees", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SandalBrown)
            )
        }
    ) { padding ->
        if (trees.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().background(CreamBackground).padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("🌳", fontSize = 60.sp)
                    Spacer(Modifier.height(12.dp))
                    Text("No trees registered yet.", color = TextMuted, fontSize = 15.sp)
                    Text("Go to Home → Register Tree", color = TextMuted, fontSize = 13.sp)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().background(CreamBackground).padding(padding),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(trees) { tree -> TreeCard(tree = tree, onClick = { onTreeClick(tree.id) }) }
            }
        }
    }
}

@Composable
fun TreeCard(tree: Tree, onClick: () -> Unit) {
    val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(3.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(SandalBrown),
                contentAlignment = Alignment.Center
            ) {
                Text("🪵", fontSize = 26.sp)
            }
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(tree.treeId, fontWeight = FontWeight.ExtraBold, color = SandalBrown, fontSize = 16.sp)
                Text("Owner: ${tree.ownerName}", fontSize = 13.sp, color = TextDark)
                Text("Girth: ${tree.girth} cm  |  Age: ~${tree.ageYears} yrs", fontSize = 12.sp, color = TextMuted)
                Text("📍 ${tree.location}", fontSize = 11.sp, color = TextMuted)
                Text("Registered: ${sdf.format(Date(tree.registeredOn))}", fontSize = 11.sp, color = TextMuted)
            }
        }
    }
}
