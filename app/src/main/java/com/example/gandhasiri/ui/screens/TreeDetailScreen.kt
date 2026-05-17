package com.example.gandhasiri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gandhasiri.ui.theme.*
import com.example.gandhasiri.viewmodel.TreeViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreeDetailScreen(
    treeId: Int,
    viewModel: TreeViewModel,
    onBack: () -> Unit
) {
    val trees by viewModel.trees.collectAsState()
    val tree = trees.find { it.id == treeId }
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (tree == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = SandalBrown)
        }
        return
    }

    val yearsLeft = viewModel.heartwoodEstimateYears(tree.girth, tree.ageYears)
    val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Tree Record?") },
            text = { Text("This will permanently remove ${tree.treeId} from the register.") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteTree(tree)
                    showDeleteDialog = false
                    onBack()
                }) { Text("Delete", color = AlertRed) }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) { Text("Cancel") }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(tree.treeId, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
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
            // Tree ID badge
            Card(
                colors = CardDefaults.cardColors(containerColor = SandalBrown),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("🪵", fontSize = 44.sp)
                    Spacer(Modifier.width(14.dp))
                    Column {
                        Text(tree.treeId, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                        Text("Registered on ${sdf.format(Date(tree.registeredOn))}", color = Color.White.copy(0.8f), fontSize = 11.sp)
                    }
                }
            }

            // Details
            DetailRow("👤 Owner", tree.ownerName)
            DetailRow("📍 Location", tree.location)
            DetailRow("📏 Girth", "${tree.girth} cm")
            DetailRow("🗓️ Age", "~${tree.ageYears} years")
            if (tree.notes.isNotBlank()) DetailRow("📝 Notes", tree.notes)

            // Maturity Calculator
            Card(
                colors = CardDefaults.cardColors(containerColor = if (yearsLeft == 0) SafeGreen else WoodLight.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("⏳ Heartwood Maturity Estimate", fontWeight = FontWeight.Bold, color = BarkDark, fontSize = 14.sp)
                    Spacer(Modifier.height(6.dp))
                    if (yearsLeft == 0) {
                        Text("✅ This tree may be mature enough for legal harvest. Consult the Forest Department.", color = Color.White, fontSize = 13.sp)
                    } else {
                        Text("Estimated $yearsLeft more year(s) until heartwood is ready.", color = BarkDark, fontSize = 13.sp)
                        Text("(Based on girth ${tree.girth}cm and age ${tree.ageYears}yrs)", color = TextMuted, fontSize = 11.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(label, fontSize = 12.sp, color = TextMuted, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(3.dp))
            Text(value, fontSize = 15.sp, color = TextDark, fontWeight = FontWeight.SemiBold)
        }
    }
}
