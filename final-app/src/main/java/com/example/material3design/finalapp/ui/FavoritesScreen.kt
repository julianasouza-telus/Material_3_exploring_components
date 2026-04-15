package com.example.material3design.finalapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Material3DesignTheme
import com.example.material3design.finalapp.data.Task
import com.example.material3design.finalapp.data.sampleTasks

@Composable
fun FavoritesScreen(
    tasks: List<Task>,
    onToggleComplete: (String) -> Unit,
    onToggleImportant: (String) -> Unit,
    onDeleteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (tasks.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f)
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "No favorites yet",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Tap the star on a task to add it here",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks, key = { it.id }) { task ->
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

@Preview(name = "FavoritesScreen — Light", showBackground = true, backgroundColor = 0xFFF5F5F9, heightDp = 500)
@Composable
private fun FavoritesScreenLightPreview() {
    Material3DesignTheme {
        FavoritesScreen(
            tasks = sampleTasks.filter { it.important },
            onToggleComplete = {},
            onToggleImportant = {},
            onDeleteClick = {}
        )
    }
}

@Preview(name = "FavoritesScreen — Dark", showBackground = true, backgroundColor = 0xFF1C1B1F, heightDp = 500)
@Composable
private fun FavoritesScreenDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        FavoritesScreen(
            tasks = sampleTasks.filter { it.important },
            onToggleComplete = {},
            onToggleImportant = {},
            onDeleteClick = {}
        )
    }
}

@Preview(name = "FavoritesScreen — Empty State", showBackground = true, backgroundColor = 0xFFF5F5F9, heightDp = 500)
@Composable
private fun FavoritesScreenEmptyPreview() {
    Material3DesignTheme {
        FavoritesScreen(
            tasks = emptyList(),
            onToggleComplete = {},
            onToggleImportant = {},
            onDeleteClick = {}
        )
    }
}