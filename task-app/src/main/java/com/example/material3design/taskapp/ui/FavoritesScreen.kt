package com.example.material3design.taskapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Material3DesignTheme
import com.example.material3design.taskapp.TaskCard
import com.example.material3design.taskapp.data.TaskData
import com.example.material3design.taskapp.data.sampleTasks

@Composable
fun FavoritesScreen(
    tasks: List<TaskData>,
    onToggleComplete: (String) -> Unit,
    onToggleImportant: (String) -> Unit,
    onDeleteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (tasks.isEmpty()){
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Favorite Session",
                modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f)
            )
            Spacer(modifier.height(12.dp))
            Text(
                text = "No favorites yet",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
            Spacer(modifier.height(4.dp))
            Text(
                text = "Tap the star on a task to add it here",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            )
        }
    } else {
        LazyColumn(
            modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks, key = { it.id } ) { task ->
                TaskCard(
                    task = task,
                    onToggleComplete = onToggleComplete,
                    onToggleImportant = onToggleImportant,
                    onDeleteClick = onDeleteClick
                )
            }
        }
    }
}

@Preview(name = "FavoriteScreen", showBackground = true, backgroundColor = 0xFFF5F5F9, heightDp = 500)
@Composable
private fun FavoriteScreenPreview(){
    Material3DesignTheme {
        FavoritesScreen(
            tasks = sampleTasks.filter { it.important },
            onToggleComplete = {},
            onToggleImportant = {},
            onDeleteClick = {}
        )
    }
}

@Preview(name = "FavoriteScreen - Empty State", showBackground = true, backgroundColor = 0xFFF5F5F9, heightDp = 500)
@Composable
private fun FavoriteScreenEmptyPreview(){
    Material3DesignTheme {
        FavoritesScreen(
            tasks = emptyList(),
            onToggleComplete = {},
            onToggleImportant = {},
            onDeleteClick = {}
        )
    }
}

