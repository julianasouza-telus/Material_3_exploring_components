package com.example.material3design.dataInput

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Material3DesignTheme

@Composable
fun CheckboxesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Checkboxes", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("Basic States")
        BasicCheckboxSection()

        SectionTitle("With Labels")
        LabeledCheckboxSection()

        SectionTitle("Tristate / Parent-Child")
        TristateCheckboxSection()

        SectionTitle("Disabled States")
        DisabledCheckboxSection()
    }
}

/* ---------- BASIC ---------- */

@Composable
private fun BasicCheckboxSection() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var unchecked by remember { mutableStateOf(false) }
        var checked by remember { mutableStateOf(true) }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Checkbox(checked = unchecked, onCheckedChange = { unchecked = it })
            Text("Unchecked", style = MaterialTheme.typography.labelSmall)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Checkbox(checked = checked, onCheckedChange = { checked = it })
            Text("Checked", style = MaterialTheme.typography.labelSmall)
        }
    }
}

/* ---------- LABELED ---------- */

@Composable
private fun LabeledCheckboxSection() {
    val options = listOf("Receber notificações", "Aceitar termos de uso", "Salvar preferências")
    val states = remember { mutableStateListOf(true, false, false) }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        options.forEachIndexed { index, label ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = states[index],
                    onCheckedChange = { states[index] = it }
                )
                Spacer(Modifier.width(8.dp))
                Text(label, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

/* ---------- TRISTATE ---------- */

@Composable
private fun TristateCheckboxSection() {
    val children = remember { mutableStateListOf(false, false, false) }
    val parentState = when {
        children.all { it } -> ToggleableState.On
        children.none { it } -> ToggleableState.Off
        else -> ToggleableState.Indeterminate
    }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TriStateCheckbox(
                state = parentState,
                onClick = {
                    val toggle = parentState != ToggleableState.On
                    repeat(children.size) { children[it] = toggle }
                }
            )
            Spacer(Modifier.width(8.dp))
            Text("Selecionar tudo", style = MaterialTheme.typography.bodyLarge)
        }
        HorizontalDivider(modifier = Modifier.padding(start = 16.dp))
        listOf("Item A", "Item B", "Item C").forEachIndexed { index, label ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 24.dp)
            ) {
                Checkbox(checked = children[index], onCheckedChange = { children[index] = it })
                Spacer(Modifier.width(8.dp))
                Text(label, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

/* ---------- DISABLED ---------- */

@Composable
private fun DisabledCheckboxSection() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = false, onCheckedChange = null)
            Spacer(Modifier.width(8.dp))
            Text("Desabilitado", style = MaterialTheme.typography.bodyMedium)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = true, onCheckedChange = null)
            Spacer(Modifier.width(8.dp))
            Text("Marcado", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

/* ---------- UTILS ---------- */

@Composable
private fun SectionTitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleMedium)
}

/* ---------- PREVIEWS ---------- */

@Preview(name = "Light", showBackground = true)
@Composable
private fun CheckboxesLightPreview() {
    Material3DesignTheme {
        CheckboxesScreen()
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun CheckboxesDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        CheckboxesScreen()
    }
}
