package com.example.gandhasiri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gandhasiri.data.Tree
import com.example.gandhasiri.ui.theme.*
import com.example.gandhasiri.viewmodel.TreeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterTreeScreen(
    viewModel: TreeViewModel,
    onBack: () -> Unit
) {
    val count by viewModel.treeCount.collectAsState()

    var ownerName by remember { mutableStateOf("") }
    var girth by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var saved by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf("") }

    if (saved) {
        AlertDialog(
            onDismissRequest = { onBack() },
            confirmButton = {
                TextButton(onClick = { onBack() }) { Text("OK") }
            },
            title = { Text("🌳 Tree Registered!") },
            text = { Text("Tree ID: ${viewModel.generateTreeId(count - 1)}\n\nYour tree has been saved to the digital register.") }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Register New Tree", color = androidx.compose.ui.graphics.Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = androidx.compose.ui.graphics.Color.White)
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
            Text("Tree ID will be: ${viewModel.generateTreeId(count)}", color = SandalBrown, fontWeight = FontWeight.Bold, fontSize = 14.sp)

            OutlinedTextField(
                value = ownerName,
                onValueChange = { ownerName = it },
                label = { Text("Owner / Farmer Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = girth,
                onValueChange = { girth = it },
                label = { Text("Girth (cm)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Estimated Age (years)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("GPS / Location (e.g. 12.9716, 77.5946)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes (optional)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            if (error.isNotEmpty()) {
                Text(error, color = AlertRed, fontSize = 13.sp)
            }

            Button(
                onClick = {
                    val g = girth.toDoubleOrNull()
                    val a = age.toIntOrNull()
                    if (ownerName.isBlank() || g == null || a == null || location.isBlank()) {
                        error = "Please fill in all required fields with valid values."
                    } else {
                        val treeId = viewModel.generateTreeId(count)
                        viewModel.addTree(
                            Tree(
                                treeId = treeId,
                                ownerName = ownerName.trim(),
                                girth = g,
                                ageYears = a,
                                location = location.trim(),
                                notes = notes.trim()
                            )
                        )
                        saved = true
                        error = ""
                    }
                },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SandalBrown)
            ) {
                Text("Register Tree 🌳", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
