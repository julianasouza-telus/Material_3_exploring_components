package com.example.material3design.selection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Material3DesignTheme
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickersScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var showRangeDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var selectedRange by remember { mutableStateOf<Pair<Long?, Long?>>(null to null) }

    val dateFormat = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Date Pickers", style = MaterialTheme.typography.headlineMedium)

        // ---- Date Picker inline ----
        SectionTitle("Date Picker (inline)")
        val inlineState = rememberDatePickerState()
        DatePicker(
            state = inlineState,
            modifier = Modifier.fillMaxWidth()
        )

        // ---- Date Picker Dialog ----
        SectionTitle("Date Picker Dialog")
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = { showDialog = true }
            ) {
                Icon(Icons.Default.DateRange, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Selecionar data")
            }
            selectedDate?.let {
                Text(
                    "Data selecionada: ${dateFormat.format(Date(it))}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // ---- Date Range Picker ----
        SectionTitle("Date Range Picker Dialog")
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { showRangeDialog = true }) {
                Icon(Icons.Default.DateRange, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Selecionar período")
            }
            if (selectedRange.first != null && selectedRange.second != null) {
                Text(
                    "De: ${dateFormat.format(Date(selectedRange.first!!))}  " +
                            "Até: ${dateFormat.format(Date(selectedRange.second!!))}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

    // ---- Date Picker Dialog ----
    if (showDialog) {
        val dialogState = rememberDatePickerState(initialSelectedDateMillis = selectedDate)
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    selectedDate = dialogState.selectedDateMillis
                    showDialog = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) { Text("Cancelar") }
            }
        ) {
            DatePicker(state = dialogState)
        }
    }

    // ---- Date Range Picker Dialog ----
    if (showRangeDialog) {
        val rangeState = rememberDateRangePickerState()
        DatePickerDialog(
            onDismissRequest = { showRangeDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    selectedRange = rangeState.selectedStartDateMillis to rangeState.selectedEndDateMillis
                    showRangeDialog = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showRangeDialog = false }) { Text("Cancelar") }
            }
        ) {
            DateRangePicker(
                state = rangeState,
                modifier = Modifier.fillMaxWidth()
            )
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
@Preview(name = "Light", showBackground = true)
@Composable
private fun DatePickersLightPreview() {
    Material3DesignTheme {
        val state = rememberDatePickerState()
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Date Pickers", style = MaterialTheme.typography.headlineMedium)
            DatePicker(state = state)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Dark", showBackground = true)
@Composable
private fun DatePickersDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        val state = rememberDatePickerState()
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Date Pickers", style = MaterialTheme.typography.headlineMedium)
            DatePicker(state = state)
        }
    }
}
