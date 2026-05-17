package com.example.gandhasiri.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gandhasiri.data.Tree
import com.example.gandhasiri.data.TreeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TreeViewModel(private val repository: TreeRepository) : ViewModel() {

    val trees: StateFlow<List<Tree>> = repository.allTrees
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val treeCount: StateFlow<Int> = repository.treeCount
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun addTree(tree: Tree) {
        viewModelScope.launch { repository.insert(tree) }
    }

    fun deleteTree(tree: Tree) {
        viewModelScope.launch { repository.delete(tree) }
    }

    /** Simulated heartwood maturity estimate based on girth & age */
    fun heartwoodEstimateYears(girth: Double, ageYears: Int): Int {
        // Sandalwood heartwood forms roughly after 15-20 years and girth > 30cm
        val yearsLeft = maxOf(0, 20 - ageYears) + if (girth < 30) 5 else 0
        return yearsLeft
    }

    /** Generate unique Tree ID */
    fun generateTreeId(existingCount: Int): String {
        return "GS-%04d".format(existingCount + 1)
    }
}

class TreeViewModelFactory(private val repository: TreeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TreeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TreeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
