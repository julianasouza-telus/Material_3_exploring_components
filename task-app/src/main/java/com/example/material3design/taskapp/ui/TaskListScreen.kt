package com.example.material3design.taskapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Material3DesignTheme
import com.example.material3design.taskapp.data.TaskData
import com.example.material3design.taskapp.data.sampleTasks

@Composable
fun TaskListScreen(
    tasks: List<TaskData>,
    filter: String,
    onFilterChange: (String) -> Unit,
    onToggleComplete: (String) -> Unit,
    onToggleImportant: (String) -> Unit,
    onDeleteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(
                selected = filter == "all",
                onClick = { onFilterChange("all")},
                label = { Text("All") }
            )
            FilterChip(
                selected = filter == "important",
                onClick = {onFilterChange("important")},
                label = {Text("Important")}
            )
            FilterChip(
                selected = filter == "completed",
                onClick = {onFilterChange("completed")},
                label = { Text("Done")}
            )
        }

        val filteredTasks = when (filter){
            "important" -> tasks.filter { it.important }
            "completed" -> tasks.filter { it.completed }
            else -> tasks
        }

        if (filteredTasks.isEmpty()){
            Column(
                modifier = Modifier.weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircleOutline,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha= 0.3f)
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = when (filter){
                        "important" -> "No important tasks yet"
                        "completed" -> "No completed tasks yet"
                        else  -> "No tasks yet. Tap + to add one!"
                    },
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredTasks, key = { it.id}) { task ->
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

@Preview(name = "TaskListScreen", showBackground = true, backgroundColor = 0xFFF5F5F9, heightDp = 600)
@Composable
private fun TaskListScreenPreview(){
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