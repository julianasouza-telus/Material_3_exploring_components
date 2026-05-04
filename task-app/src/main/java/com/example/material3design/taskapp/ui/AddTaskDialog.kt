package com.example.material3design.taskapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Material3DesignTheme

@Composable
fun AddTaskDialog(
    onDismiss: () -> Unit,
    onSave: (title: String, description: String, important: Boolean) -> Unit
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("")}
    var important by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("New Task")},
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = {title = it },
                    label = { Text("Title *")},
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = {description = it},
                    label = { Text("Description")},
                    placeholder = { Text("Optional details")},
                    minLines = 3,
                    maxLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Mark as important",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Switch(
                        checked = important,
                        onCheckedChange = {important = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = MaterialTheme.colorScheme.secondary,
                            checkedTrackColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)
                        )
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onSave(title.trim(), description.trim(), important)},
                enabled = title.isNotBlank()
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Preview(name = "AddTaskDialog")
@Composable
private fun AddTaskDialogPreview(){
    Material3DesignTheme() {
        AddTaskDialog(onDismiss = {}, onSave = {_, _,_ -> } )
    }
}
