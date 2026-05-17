package com.example.gandhasiri.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trees")
data class Tree(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val treeId: String,           // Unique ID like "GS-0001"
    val ownerName: String,
    val location: String,         // GPS coordinates as "lat,lng" string
    val girth: Double,            // Girth in cm
    val ageYears: Int,            // Estimated age in years
    val photoPath: String = "",   // Local path to photo
    val notes: String = "",
    val registeredOn: Long = System.currentTimeMillis()
)
