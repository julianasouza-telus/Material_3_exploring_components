package com.example.material3design.communication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicatorsScreen() {
    var progress by remember { mutableStateOf(0.4f) }
    var loading by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text("Progress Indicators", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("Circular Indicators")
        CircularSection(progress, loading)

        SectionTitle("Linear Indicators")
        LinearSection(progress, loading)

        SectionTitle("States & Controls")
        ControlsSection(
            progress = progress,
            onProgressChange = { progress = it },
            loading = loading,
            onLoadingChange = { loading = it }
        )
    }
}

/* ---------- CIRCULAR ---------- */

@Composable
private fun CircularSection(progress: Float, loading: Boolean) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Indeterminate
        CircularProgressIndicator()

        // Determinate
        CircularProgressIndicator(progress = progress)

        // Small size
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier.size(24.dp),
            strokeWidth = 2.dp
        )

        // Custom color
        CircularProgressIndicator(
            progress = progress,
            color = MaterialTheme.colorScheme.tertiary
        )

        // Disabled (visual example)
        CircularProgressIndicator(
            progress = progress,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        )
    }
}

/* ---------- LINEAR ---------- */

@Composable
private fun LinearSection(progress: Float, loading: Boolean) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Indeterminate
        LinearProgressIndicator()

        // Determinate
        LinearProgressIndicator(progress = progress)

        // Custom height
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )

        // Custom color
        LinearProgressIndicator(
            progress = progress,
            color = MaterialTheme.colorScheme.secondary
        )

        // Track + color customization
        LinearProgressIndicator(
            progress = progress,
            color = MaterialTheme.colorScheme.tertiary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

/* ---------- CONTROLS ---------- */

@Composable
private fun ControlsSection(
    progress: Float,
    onProgressChange: (Float) -> Unit,
    loading: Boolean,
    onLoadingChange: (Boolean) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Progress: ${(progress * 100).toInt()}%")

        Slider(
            value = progress,
            onValueChange = onProgressChange,
            valueRange = 0f..1f
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(checked = loading, onCheckedChange = onLoadingChange)
            Spacer(Modifier.width(8.dp))
            Text(if (loading) "Indeterminate" else "Determinate")
        }
    }
}

/* ---------- UTILS ---------- */

@Composable
private fun SectionTitle(title: String) {
    Text(text = title, style = MaterialTheme.typography.titleMedium)
}

/* ---------- PREVIEWS ---------- */

@Preview(showBackground = true)
@Composable
private fun ProgressIndicatorsScreenPreview() {
    MaterialTheme {
        ProgressIndicatorsScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun ProgressIndicatorsDarkPreview() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        ProgressIndicatorsScreen()
    }
}
