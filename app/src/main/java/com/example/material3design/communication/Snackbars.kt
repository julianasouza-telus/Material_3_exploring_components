package com.example.material3design.communication

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.ui.theme.Material3DesignTheme
import kotlinx.coroutines.launch

@Composable
fun SnackbarsScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text("Snackbars", style = MaterialTheme.typography.headlineSmall)

            Button(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("Basic Snackbar")
                }
            }) {
                Text("Show Basic Snackbar")
            }

            Button(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Snackbar with Action",
                        actionLabel = "Undo"
                    )
                }
            }) {
                Text("Show Snackbar with Action")
            }

            Button(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Snackbar with Dismiss",
                        withDismissAction = true
                    )
                }
            }) {
                Text("Show Snackbar with Dismiss")
            }

            Button(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Indefinite Snackbar",
                        duration = SnackbarDuration.Indefinite,
                        actionLabel = "Retry"
                    )
                }
            }) {
                Text("Show Indefinite Snackbar")
            }
        }
    }
}

// ---------- Previews with visible Snackbars ----------

@Preview(showBackground = true)
@Composable
fun BasicSnackbarScreenPreview() {
    Material3DesignTheme {
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = remember { SnackbarHostState() }
                ) {
                    Snackbar {
                        Text("This is a basic snackbar")
                    }
                }
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Screen content",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SnackbarWithActionPreview() {
    Material3DesignTheme {
        Snackbar(
            action = {
                TextButton(onClick = {}) {
                    Text("Undo")
                }
            }
        ) {
            Text("Snackbar with Action")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SnackbarWithDismissPreview() {
    Material3DesignTheme {
        Snackbar(
            dismissAction = {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Close, contentDescription = "Dismiss")
                }
            }
        ) {
            Text("Snackbar with Dismiss")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IndefiniteSnackbarPreview() {
    Material3DesignTheme {
        Snackbar(
            action = {
                TextButton(onClick = {}) {
                    Text("Retry")
                }
            }
        ) {
            Text("Indefinite Snackbar — persists until action")
        }
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
fun SnackbarsDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        Snackbar(
            action = {
                TextButton(onClick = {}) { Text("OK") }
            }
        ) {
            Text("Dark mode snackbar")
        }
    }
}
