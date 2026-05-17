package com.example.gandhasiri.data

import kotlinx.coroutines.flow.Flow

class TreeRepository(private val treeDao: TreeDao) {
    val allTrees: Flow<List<Tree>> = treeDao.getAllTrees()
    val treeCount: Flow<Int> = treeDao.getTreeCount()

    suspend fun insert(tree: Tree) = treeDao.insertTree(tree)
    suspend fun delete(tree: Tree) = treeDao.deleteTree(tree)
    suspend fun getById(id: Int): Tree? = treeDao.getTreeById(id)
}
