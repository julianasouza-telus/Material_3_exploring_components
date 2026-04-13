package com.example.material3design.dataInput

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.ui.theme.Material3DesignTheme

@Composable
fun RadioButtonsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Radio Buttons", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("Seleção Única")
        SingleSelectionSection()

        SectionTitle("Grupo Horizontal")
        HorizontalGroupSection()

        SectionTitle("Com Descrição")
        WithDescriptionSection()

        SectionTitle("Estados Desabilitados")
        DisabledSection()
    }
}

/* ---------- SINGLE SELECTION ---------- */

@Composable
private fun SingleSelectionSection() {
    val options = listOf("Opção 1", "Opção 2", "Opção 3")
    var selected by remember { mutableStateOf(options[0]) }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(
                    selected = selected == option,
                    onClick = { selected = option }
                )
                Spacer(Modifier.width(8.dp))
                Text(option, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

/* ---------- HORIZONTAL GROUP ---------- */

@Composable
private fun HorizontalGroupSection() {
    val sizes = listOf("PP", "P", "M", "G", "GG")
    var selected by remember { mutableStateOf(sizes[2]) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        sizes.forEach { size ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                RadioButton(
                    selected = selected == size,
                    onClick = { selected = size }
                )
                Text(size, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

/* ---------- WITH DESCRIPTION ---------- */

@Composable
private fun WithDescriptionSection() {
    data class Plan(val name: String, val price: String, val description: String)

    val plans = listOf(
        Plan("Básico", "Grátis", "Acesso a recursos essenciais"),
        Plan("Pro", "R$ 29/mês", "Recursos avançados e suporte"),
        Plan("Enterprise", "Consulte", "Solução completa para empresas"),
    )
    var selectedPlan by remember { mutableStateOf(plans[0].name) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        plans.forEach { plan ->
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(
                    selected = selectedPlan == plan.name,
                    onClick = { selectedPlan = plan.name }
                )
                Spacer(Modifier.width(8.dp))
                Column {
                    Text(plan.name, style = MaterialTheme.typography.bodyLarge)
                    Text(plan.price, style = MaterialTheme.typography.bodyMedium)
                    Text(
                        plan.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            if (plan != plans.last()) HorizontalDivider()
        }
    }
}

/* ---------- DISABLED ---------- */

@Composable
private fun DisabledSection() {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = false, onClick = null, enabled = false)
            Spacer(Modifier.width(8.dp))
            Text("Não selecionado (desabilitado)", style = MaterialTheme.typography.bodyMedium)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = true, onClick = null, enabled = false)
            Spacer(Modifier.width(8.dp))
            Text("Selecionado (desabilitado)", style = MaterialTheme.typography.bodyMedium)
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
private fun RadioButtonsLightPreview() {
    Material3DesignTheme {
        RadioButtonsScreen()
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun RadioButtonsDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        RadioButtonsScreen()
    }
}
