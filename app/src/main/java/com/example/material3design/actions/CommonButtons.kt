package com.example.material3design.actions

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.ui.theme.Material3DesignTheme

@Composable
fun CommonButtonsShowcase() {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {

        Text("Common Buttons", style = MaterialTheme.typography.titleLarge)

        // ----------------------------
        // Filled / Primary Buttons
        // ----------------------------
        SectionTitle("Filled Buttons")
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {}) { Text("Default") }
            Button(onClick = {}, enabled = false) { Text("Disabled") }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {}, contentPadding = PaddingValues(horizontal = 24.dp)) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("With Icon")
            }
            Button(onClick = {}) {
                Text("Loading")
                Spacer(Modifier.width(8.dp))
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Full Width")
        }

        // ----------------------------
        // Filled Tonal Buttons
        // ----------------------------
        SectionTitle("Filled Tonal Buttons")
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            FilledTonalButton(onClick = {}) { Text("Default") }
            FilledTonalButton(onClick = {}, enabled = false) { Text("Disabled") }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            FilledTonalButton(onClick = {}) {
                Icon(Icons.Default.Check, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("With Icon")
            }
        }

        // ----------------------------
        // Outlined Buttons
        // ----------------------------
        SectionTitle("Outlined Buttons")
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedButton(onClick = {}) { Text("Default") }
            OutlinedButton(onClick = {}, enabled = false) { Text("Disabled") }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("With Icon")
            }
        }

        // ----------------------------
        // Text Buttons
        // ----------------------------
        SectionTitle("Text Buttons")
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            TextButton(onClick = {}) { Text("Default") }
            TextButton(onClick = {}, enabled = false) { Text("Disabled") }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            TextButton(onClick = {}) {
                Icon(Icons.Default.Close, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("With Icon")
            }
        }

        // ----------------------------
        // Elevated Buttons
        // ----------------------------
        SectionTitle("Elevated Buttons")
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ElevatedButton(onClick = {}) { Text("Default") }
            ElevatedButton(onClick = {}, enabled = false) { Text("Disabled") }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ElevatedButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("With Icon")
            }
        }

        // ----------------------------
        // Button Shapes & Sizes
        // ----------------------------
        SectionTitle("Sizes & Shapes")
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = {},
                shape = MaterialTheme.shapes.extraLarge,
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text("Large")
            }

            Button(
                onClick = {},
                shape = MaterialTheme.shapes.small,
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text("Small")
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
private fun CommonButtonsLightPreview() {
    Material3DesignTheme {
        CommonButtonsShowcase()
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun CommonButtonsDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        CommonButtonsShowcase()
    }
}
