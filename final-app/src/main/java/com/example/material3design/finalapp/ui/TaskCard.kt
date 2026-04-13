package com.example.material3design.finalapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Material3DesignTheme
import com.example.material3design.core.ui.theme.TaskFlowSecondary
import com.example.material3design.core.ui.theme.TaskFlowSuccess
import com.example.material3design.finalapp.data.Task

@Composable
fun TaskCard(
    task: Task,
    onToggleComplete: (String) -> Unit,
    onToggleImportant: (String) -> Unit,
    onDeleteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.Top
        ) {
            Checkbox(
                checked = task.completed,
                onCheckedChange = { onToggleComplete(task.id) },
                modifier = Modifier.padding(top = 4.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 10.dp, bottom = 8.dp, end = 4.dp)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.bodyLarge,
                    textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None,
                    color = if (task.completed)
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    else
                        MaterialTheme.colorScheme.onSurface
                )
                if (task.description.isNotEmpty()) {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None,
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = if (task.completed) 0.38f else 0.6f
                        ),
                        maxLines = 2
                    )
                }
                if (task.important || task.completed) {
                    Spacer(Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        if (task.important) {
                            SuggestionChip(
                                onClick = {},
                                label = { Text("Important", style = MaterialTheme.typography.labelSmall) },
                                colors = SuggestionChipDefaults.suggestionChipColors(
                                    containerColor = TaskFlowSecondary.copy(alpha = 0.15f),
                                    labelColor = TaskFlowSecondary
                                ),
                                border = SuggestionChipDefaults.suggestionChipBorder(
                                    enabled = true,
                                    borderColor = TaskFlowSecondary.copy(alpha = 0.4f)
                                )
                            )
                        }
                        if (task.completed) {
                            SuggestionChip(
                                onClick = {},
                                label = { Text("Completed", style = MaterialTheme.typography.labelSmall) },
                                colors = SuggestionChipDefaults.suggestionChipColors(
                                    containerColor = TaskFlowSuccess.copy(alpha = 0.15f),
                                    labelColor = TaskFlowSuccess
                                ),
                                border = SuggestionChipDefaults.suggestionChipBorder(
                                    enabled = true,
                                    borderColor = TaskFlowSuccess.copy(alpha = 0.4f)
                                )
                            )
                        }
                    }
                }
            }

            IconButton(onClick = { onToggleImportant(task.id) }) {
                Icon(
                    imageVector = if (task.important) Icons.Filled.Star else Icons.Outlined.StarOutline,
                    contentDescription = if (task.important) "Remove from favorites" else "Add to favorites",
                    tint = if (task.important)
                        TaskFlowSecondary
                    else
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                )
            }

            IconButton(onClick = { onDeleteClick(task.id) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete task",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}


private val previewTaskNormal = Task(
    id = "1",
    title = "Review Material Design 3 guidelines",
    description = "Study the latest MD3 specifications and component patterns"
)

private val previewTaskImportant = Task(
    id = "2",
    title = "Build TaskFlow prototype",
    description = "Create a clean UI showcasing MD3 components",
    important = true
)

private val previewTaskCompleted = Task(
    id = "3",
    title = "Test dark theme support",
    description = "Ensure all components work well in dark mode",
    completed = true
)

private val previewTaskBoth = Task(
    id = "4",
    title = "Write unit tests",
    description = "Cover all ViewModels and use-cases",
    important = true,
    completed = true
)

@Preview(name = "TaskCard — Light", showBackground = true, backgroundColor = 0xFFF5F5F9)
@Composable
private fun TaskCardNormalLightPreview() {
    Material3DesignTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TaskCard(task = previewTaskNormal, onToggleComplete = {}, onToggleImportant = {}, onDeleteClick = {})
            TaskCard(task = previewTaskImportant, onToggleComplete = {}, onToggleImportant = {}, onDeleteClick = {})
            TaskCard(task = previewTaskCompleted, onToggleComplete = {}, onToggleImportant = {}, onDeleteClick = {})
            TaskCard(task = previewTaskBoth, onToggleComplete = {}, onToggleImportant = {}, onDeleteClick = {})
        }
    }
}

@Preview(name = "TaskCard — Dark", showBackground = true, backgroundColor = 0xFF1C1B1F)
@Composable
private fun TaskCardNormalDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TaskCard(task = previewTaskNormal, onToggleComplete = {}, onToggleImportant = {}, onDeleteClick = {})
            TaskCard(task = previewTaskImportant, onToggleComplete = {}, onToggleImportant = {}, onDeleteClick = {})
            TaskCard(task = previewTaskCompleted, onToggleComplete = {}, onToggleImportant = {}, onDeleteClick = {})
            TaskCard(task = previewTaskBoth, onToggleComplete = {}, onToggleImportant = {}, onDeleteClick = {})
        }
    }
}