package com.example.material3design.actions

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Material3DesignTheme

@Composable
fun FabShowcase() {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {

        Text("Floating Action Buttons", style = MaterialTheme.typography.titleLarge)

        // ----------------------------
        // Standard FAB sizes
        // ----------------------------
        SectionTitle("Standard FAB Sizes")
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SmallFloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = null)
            }

            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = null)
            }

            LargeFloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }

        // ----------------------------
        // Extended FAB
        // ----------------------------
        SectionTitle("Extended FAB")
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            ExtendedFloatingActionButton(
                onClick = {},
                icon = { Icon(Icons.Default.Edit, contentDescription = null) },
                text = { Text("Edit") }
            )

            ExtendedFloatingActionButton(
                onClick = {},
                icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                text = { Text("Favorite") }
            )
        }

        // ----------------------------
        // Disabled states
        // ----------------------------
        SectionTitle("Disabled State")
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = null)
            }

            ExtendedFloatingActionButton(
                onClick = {},
                icon = { Icon(Icons.Default.Edit, contentDescription = null) },
                text = { Text("Disabled") }
            )
        }

        // ----------------------------
        // FAB with loading state
        // ----------------------------
        SectionTitle("Loading State")
        FloatingActionButton(onClick = {}) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                strokeWidth = 2.dp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        // ----------------------------
        // Custom shapes & colors
        // ----------------------------
        SectionTitle("Custom Shape & Color")
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            FloatingActionButton(
                onClick = {},
                shape = MaterialTheme.shapes.extraLarge,
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }

            FloatingActionButton(
                onClick = {},
                shape = MaterialTheme.shapes.small,
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            ) {
                Icon(Icons.Default.Favorite, contentDescription = null)
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleMedium)
}

@Preview(name = "Light", showBackground = true)
@Composable
private fun FabLightPreview() {
    Material3DesignTheme {
        FabShowcase()
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun FabDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        FabShowcase()
    }
}
