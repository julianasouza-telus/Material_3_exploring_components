package com.example.material3design.communication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Material3DesignTheme

@Composable
fun ProgressIndicatorsScreen() {
    var progress by remember { mutableStateOf(0.4f) }
    var indeterminate by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text("Progress Indicators", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("Circular Indicators")
        CircularSection(progress = progress, indeterminate = indeterminate)

        SectionTitle("Linear Indicators")
        LinearSection(progress = progress, indeterminate = indeterminate)

        SectionTitle("Controls")
        ControlsSection(
            progress = progress,
            onProgressChange = { progress = it },
            indeterminate = indeterminate,
            onIndeterminateChange = { indeterminate = it }
        )
    }
}

/* ---------- CIRCULAR ---------- */

@Composable
private fun CircularSection(progress: Float, indeterminate: Boolean) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            if (indeterminate) CircularProgressIndicator()
            else CircularProgressIndicator(progress = { progress })
            Text("Default", style = MaterialTheme.typography.labelSmall)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            if (indeterminate) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )
            } else {
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )
            }
            Text("Small", style = MaterialTheme.typography.labelSmall)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            if (indeterminate) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.tertiary)
            } else {
                CircularProgressIndicator(
                    progress = { progress },
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            Text("Tertiary", style = MaterialTheme.typography.labelSmall)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            CircularProgressIndicator(
                progress = { progress },
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
            Text("Muted", style = MaterialTheme.typography.labelSmall)
        }
    }
}

/* ---------- LINEAR ---------- */

@Composable
private fun LinearSection(progress: Float, indeterminate: Boolean) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            if (indeterminate) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            else LinearProgressIndicator(progress = { progress }, modifier = Modifier.fillMaxWidth())
            Text("Default", style = MaterialTheme.typography.labelSmall)
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            if (indeterminate) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth().height(8.dp)
                )
            } else {
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth().height(8.dp)
                )
            }
            Text("Thick (8 dp)", style = MaterialTheme.typography.labelSmall)
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            if (indeterminate) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.secondary
                )
            } else {
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }
            Text("Secondary color", style = MaterialTheme.typography.labelSmall)
        }
    }
}

/* ---------- CONTROLS ---------- */

@Composable
private fun ControlsSection(
    progress: Float,
    onProgressChange: (Float) -> Unit,
    indeterminate: Boolean,
    onIndeterminateChange: (Boolean) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Progress")
            Text("${(progress * 100).toInt()}%")
        }
        Slider(
            value = progress,
            onValueChange = onProgressChange,
            enabled = !indeterminate
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(checked = indeterminate, onCheckedChange = onIndeterminateChange)
            Spacer(Modifier.width(8.dp))
            Text(if (indeterminate) "Indeterminate" else "Determinate")
        }
    }
}

/* ---------- UTILS ---------- */

@Composable
private fun SectionTitle(title: String) {
    Text(text = title, style = MaterialTheme.typography.titleMedium)
}

/* ---------- PREVIEWS ---------- */

@Preview(name = "Light", showBackground = true)
@Composable
private fun ProgressIndicatorsLightPreview() {
    Material3DesignTheme {
        ProgressIndicatorsScreen()
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun ProgressIndicatorsDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        ProgressIndicatorsScreen()
    }
}
