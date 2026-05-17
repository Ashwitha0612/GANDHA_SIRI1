package com.example.gandhasiri.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TreeDao {
    @Query("SELECT * FROM trees ORDER BY registeredOn DESC")
    fun getAllTrees(): Flow<List<Tree>>

    @Query("SELECT * FROM trees WHERE id = :id")
    suspend fun getTreeById(id: Int): Tree?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTree(tree: Tree)

    @Delete
    suspend fun deleteTree(tree: Tree)

    @Query("SELECT COUNT(*) FROM trees")
    fun getTreeCount(): Flow<Int>
}
