package com.example.material3design.containment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.material3design.ui.theme.Material3DesignTheme

@Composable
fun DialogsScreen() {
    var showBasic by remember { mutableStateOf(false) }
    var showWithIcon by remember { mutableStateOf(false) }
    var showWithInput by remember { mutableStateOf(false) }
    var showDestructive by remember { mutableStateOf(false) }
    var showLoading by remember { mutableStateOf(false) }
    var showFullscreen by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Dialogs", style = MaterialTheme.typography.headlineSmall)

        Text("Alert Dialogs", style = MaterialTheme.typography.titleMedium)

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { showBasic = true }) { Text("Basic") }
            Button(onClick = { showWithIcon = true }) { Text("With Icon") }
            Button(onClick = { showWithInput = true }) { Text("With Input") }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { showDestructive = true }) { Text("Destructive") }
            Button(onClick = { showLoading = true }) { Text("Loading") }
        }

        Spacer(Modifier.height(24.dp))

        Text("Full Screen Dialog", style = MaterialTheme.typography.titleMedium)

        Button(onClick = { showFullscreen = true }) {
            Text("Open Fullscreen Dialog")
        }
    }

    if (showBasic) BasicDialog(onDismiss = { showBasic = false })
    if (showWithIcon) IconDialog(onDismiss = { showWithIcon = false })
    if (showWithInput) InputDialog(onDismiss = { showWithInput = false })
    if (showDestructive) DestructiveDialog(onDismiss = { showDestructive = false })
    if (showLoading) LoadingDialog(onDismiss = { showLoading = false })
    if (showFullscreen) FullscreenDialog(onDismiss = { showFullscreen = false })
}

// ---------- Dialog Variants ----------

@Composable
private fun BasicDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Basic Dialog") },
        text = { Text("This is a simple alert dialog.") },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("OK") }
        }
    )
}

@Composable
private fun IconDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(Icons.Default.Info, contentDescription = null)
        },
        title = { Text("Dialog with Icon") },
        text = { Text("This dialog includes an icon for emphasis.") },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Got it") }
        }
    )
}

@Composable
private fun InputDialog(onDismiss: () -> Unit) {
    var text by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Input Dialog") },
        text = {
            Column {
                Text("Enter some text:")
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Submit") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@Composable
private fun DestructiveDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(Icons.Default.Delete, contentDescription = null)
        },
        title = { Text("Delete Item") },
        text = { Text("This action cannot be undone. Are you sure?") },
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@Composable
private fun LoadingDialog(onDismiss: () -> Unit = {}) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Loading") },
        text = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CircularProgressIndicator()
                Text("Please wait...")
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@Composable
private fun FullscreenDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            tonalElevation = 3.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Full Screen Dialog", style = MaterialTheme.typography.titleLarge)
                    TextButton(onClick = onDismiss) { Text("Close") }
                }

                Text("This dialog takes up the full screen and is used for complex tasks.")

                Button(onClick = {}) {
                    Text("Primary Action")
                }

                OutlinedButton(onClick = {}) {
                    Text("Secondary Action")
                }
            }
        }
    }
}

// ---------- Previews ----------

@Preview(name = "Basic", showBackground = true)
@Composable
private fun BasicDialogPreview() {
    Material3DesignTheme { BasicDialog(onDismiss = {}) }
}

@Preview(name = "With Icon", showBackground = true)
@Composable
private fun IconDialogPreview() {
    Material3DesignTheme { IconDialog(onDismiss = {}) }
}

@Preview(name = "With Input", showBackground = true)
@Composable
private fun InputDialogPreview() {
    Material3DesignTheme { InputDialog(onDismiss = {}) }
}

@Preview(name = "Destructive", showBackground = true)
@Composable
private fun DestructiveDialogPreview() {
    Material3DesignTheme { DestructiveDialog(onDismiss = {}) }
}

@Preview(name = "Loading", showBackground = true)
@Composable
private fun LoadingDialogPreview() {
    Material3DesignTheme { LoadingDialog() }
}

@Preview(name = "Fullscreen", showBackground = true)
@Composable
private fun FullscreenDialogPreview() {
    Material3DesignTheme { FullscreenDialog(onDismiss = {}) }
}

@Preview(name = "Dark — Basic", showBackground = true)
@Composable
private fun DialogsDarkPreview() {
    Material3DesignTheme(darkTheme = true) { BasicDialog(onDismiss = {}) }
}
