package com.example.material3design.finalapp.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
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
fun TaskListScreen(
    tasks: List<Task>,
    filter: String,
    onFilterChange: (String) -> Unit,
    onToggleComplete: (String) -> Unit,
    onToggleImportant: (String) -> Unit,
    onDeleteClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState()
) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = filter == "all",
                onClick = { onFilterChange("all") },
                label = { Text("All") }
            )
            FilterChip(
                selected = filter == "important",
                onClick = { onFilterChange("important") },
                label = { Text("Important") }
            )
            FilterChip(
                selected = filter == "completed",
                onClick = { onFilterChange("completed") },
                label = { Text("Done") }
            )
        }

        AnimatedContent(
            targetState = filter,
            transitionSpec = {
                (fadeIn(tween(durationMillis = 300, delayMillis = 120)) +
                        scaleIn(initialScale = 0.97f, animationSpec = tween(300, delayMillis = 120))) togetherWith
                        fadeOut(tween(durationMillis = 120))
            },
            label = "filterTransition",
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { activeFilter ->
            val filteredTasks = when (activeFilter) {
                "important" -> tasks.filter { it.important }
                "completed" -> tasks.filter { it.completed }
                else -> tasks
            }

            if (filteredTasks.isEmpty()) {
                EmptyState(
                    message = when (activeFilter) {
                        "important" -> "No important tasks yet"
                        "completed" -> "No completed tasks yet"
                        else -> "No tasks yet. Tap + to add one!"
                    },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredTasks, key = { it.id }) { task ->
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
    }
}

@Composable
private fun EmptyState(message: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircleOutline,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
    }
}

@Preview(name = "TaskListScreen — Light", showBackground = true, backgroundColor = 0xFFF5F5F9, heightDp = 600)
@Composable
private fun TaskListScreenLightPreview() {
    Material3DesignTheme {
        TaskListScreen(
            tasks = sampleTasks,
            filter = "all",
            onFilterChange = {},
            onToggleComplete = {},
            onToggleImportant = {},
            onDeleteClick = {}
        )
    }
}

@Preview(name = "TaskListScreen — Dark", showBackground = true, backgroundColor = 0xFF1C1B1F, heightDp = 600)
@Composable
private fun TaskListScreenDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        TaskListScreen(
            tasks = sampleTasks,
            filter = "all",
            onFilterChange = {},
            onToggleComplete = {},
            onToggleImportant = {},
            onDeleteClick = {}
        )
    }
}

@Preview(name = "TaskListScreen — Empty State", showBackground = true, backgroundColor = 0xFFF5F5F9, heightDp = 600)
@Composable
private fun TaskListScreenEmptyPreview() {
    Material3DesignTheme {
        TaskListScreen(
            tasks = emptyList(),
            filter = "all",
            onFilterChange = {},
            onToggleComplete = {},
            onToggleImportant = {},
            onDeleteClick = {}
        )
    }
}