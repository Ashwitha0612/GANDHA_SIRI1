package com.example.gandhasiri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.gandhasiri.data.AppDatabase
import com.example.gandhasiri.data.TreeRepository
import com.example.gandhasiri.ui.navigation.NavGraph
import com.example.gandhasiri.ui.theme.GandhaSiriTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database   = AppDatabase.getDatabase(this)
        val repository = TreeRepository(database.treeDao())

        setContent {
            GandhaSiriTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(repository)
                }
            }
        }
    }
}
