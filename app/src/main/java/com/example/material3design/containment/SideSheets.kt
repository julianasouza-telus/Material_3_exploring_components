package com.example.material3design.containment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.ui.theme.Material3DesignTheme

@Composable
fun SideSheetsScreen() {
    var showModalSheet by remember { mutableStateOf(false) }
    var showDismissibleSheet by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {

        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text("Side Sheets", style = MaterialTheme.typography.headlineSmall)

            Text("Modal Side Sheet", style = MaterialTheme.typography.titleMedium)
            Button(onClick = { showModalSheet = true }) {
                Text("Open Modal Side Sheet")
            }

            Text("Dismissible Side Sheet", style = MaterialTheme.typography.titleMedium)
            Button(onClick = { showDismissibleSheet = true }) {
                Text("Open Dismissible Side Sheet")
            }
        }

        // Modal Side Sheet (blocks background)
        if (showModalSheet) {
            ModalSideSheet(
                onDismiss = { showModalSheet = false },
                title = "Modal Side Sheet",
                isModal = true
            )
        }

        // Dismissible Side Sheet (does not block background)
        if (showDismissibleSheet) {
            ModalSideSheet(
                onDismiss = { showDismissibleSheet = false },
                title = "Dismissible Side Sheet",
                isModal = false
            )
        }
    }
}

@Composable
private fun ModalSideSheet(
    onDismiss: () -> Unit,
    title: String,
    isModal: Boolean
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isModal) {
            // Scrim
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.scrim.copy(alpha = 0.5f))
                    .clickable { onDismiss() }
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .width(320.dp)
                .align(Alignment.CenterEnd),
            tonalElevation = 3.dp,
            shape = MaterialTheme.shapes.large
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
                    Text(title, style = MaterialTheme.typography.titleLarge)
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Text(
                    "Side sheets are used for supplementary content or contextual actions. " +
                            "They slide in from the side and allow users to stay in context."
                )

                Button(onClick = {}) {
                    Text("Primary Action")
                }

                OutlinedButton(onClick = {}) {
                    Text("Secondary Action")
                }

                TextButton(onClick = {}) {
                    Text("Tertiary Action")
                }
            }
        }
    }
}

@Preview(name = "Light", showBackground = true)
@Composable
private fun SideSheetsLightPreview() {
    Material3DesignTheme {
        SideSheetsScreen()
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun SideSheetsDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        SideSheetsScreen()
    }
}
