package com.example.material3design.selection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.material3design.core.ui.theme.Material3DesignTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickersScreen() {
    var showClockDialog by remember { mutableStateOf(false) }
    var showInputDialog by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    // Estados declarados fora de blocos condicionais (regra do Compose)
    val clockDialogState = rememberTimePickerState()
    val inputDialogState = rememberTimePickerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text("Time Pickers", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("Time Picker — Relógio (inline)")
        val inlineClockState = rememberTimePickerState(initialHour = 10, initialMinute = 30)
        TimePicker(
            state = inlineClockState,
            modifier = Modifier.wrapContentWidth()
        )

        SectionTitle("Time Input — Texto (inline)")
        val inlineInputState = rememberTimePickerState(initialHour = 8, initialMinute = 0)
        TimeInput(state = inlineInputState)

        SectionTitle("Via Dialog")
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { showClockDialog = true }) {
                    Icon(Icons.Default.Schedule, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Relógio")
                }
                Button(onClick = { showInputDialog = true }) {
                    Icon(Icons.Default.Edit, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Texto")
                }
            }
            selectedTime?.let { (hour, minute) ->
                val amPm = if (hour < 12) "AM" else "PM"
                val h = if (hour % 12 == 0) 12 else hour % 12
                Text(
                    "Horário selecionado: %02d:%02d %s".format(h, minute, amPm),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

    if (showClockDialog) {
        TimePickerDialog(
            onDismiss = { showClockDialog = false },
            onConfirm = {
                selectedTime = clockDialogState.hour to clockDialogState.minute
                showClockDialog = false
            }
        ) {
            TimePicker(state = clockDialogState)
        }
    }

    if (showInputDialog) {
        TimePickerDialog(
            onDismiss = { showInputDialog = false },
            onConfirm = {
                selectedTime = inputDialogState.hour to inputDialogState.minute
                showInputDialog = false
            }
        ) {
            TimeInput(state = inputDialogState)
        }
    }
}

/* ---------- TIME PICKER DIALOG ---------- */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    "Selecionar horário",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                content()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = onDismiss) { Text("Cancelar") }
                    Spacer(Modifier.width(8.dp))
                    TextButton(onClick = onConfirm) { Text("OK") }
                }
            }
        }
    }
}

/* ---------- UTILS ---------- */

@Composable
private fun SectionTitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleMedium)
}

/* ---------- PREVIEWS ---------- */

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Light — Clock", showBackground = true)
@Composable
private fun TimePickerClockLightPreview() {
    Material3DesignTheme {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("TimePicker — Relógio", style = MaterialTheme.typography.headlineMedium)
            TimePicker(state = rememberTimePickerState(initialHour = 10, initialMinute = 30))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Light — Input", showBackground = true)
@Composable
private fun TimePickerInputLightPreview() {
    Material3DesignTheme {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("TimeInput — Texto", style = MaterialTheme.typography.headlineMedium)
            TimeInput(state = rememberTimePickerState(initialHour = 8, initialMinute = 0))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Dark — Clock", showBackground = true)
@Composable
private fun TimePickerClockDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("TimePicker — Relógio", style = MaterialTheme.typography.headlineMedium)
            TimePicker(state = rememberTimePickerState(initialHour = 14, initialMinute = 45))
        }
    }
}
