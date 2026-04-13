package com.example.material3design.layout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.ui.theme.Dimens
import com.example.material3design.ui.theme.Material3DesignTheme

@Composable
fun DividerScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Dimens.spaceMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceLarge)
    ) {
        Text("Divider", style = MaterialTheme.typography.headlineMedium)

        Text(
            "Dividers são linhas finas que agrupam conteúdo em listas e layouts. " +
                    "No M3, use HorizontalDivider e VerticalDivider.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        SectionTitle("HorizontalDivider — Padrão")
        HorizontalDivider()

        SectionTitle("HorizontalDivider — Espessuras")
        ThicknessSection()

        SectionTitle("HorizontalDivider — Recuo (indent)")
        IndentSection()

        SectionTitle("HorizontalDivider — Em lista")
        ListWithDividersSection()

        SectionTitle("VerticalDivider")
        VerticalDividerSection()
    }
}

/* ---------- SECTIONS ---------- */

@Composable
private fun ThicknessSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        listOf(1.dp to "1dp (padrão)", 2.dp to "2dp", 4.dp to "4dp").forEach { (thickness, label) ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                HorizontalDivider(
                    thickness = thickness,
                    modifier = Modifier.weight(1f)
                )
                Text(label, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
private fun IndentSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Sem indent:", style = MaterialTheme.typography.labelSmall)
        HorizontalDivider()

        Text("startIndent = 56.dp (ícone + padding):", style = MaterialTheme.typography.labelSmall)
        HorizontalDivider(modifier = Modifier.padding(start = 56.dp))

        Text("Centralizado (horizontal padding = 16.dp):", style = MaterialTheme.typography.labelSmall)
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
    }
}

@Composable
private fun ListWithDividersSection() {
    Surface(
        shape = MaterialTheme.shapes.medium,
        tonalElevation = Dimens.elevationLow
    ) {
        Column {
            listOf("Alice", "Bob", "Carol", "David").forEachIndexed { index, name ->
                ListItem(
                    headlineContent = { Text(name) },
                    supportingContent = { Text("Toque para detalhes") },
                    leadingContent = {
                        Icon(Icons.Default.Person, contentDescription = null)
                    }
                )
                if (index < 3) {
                    HorizontalDivider(modifier = Modifier.padding(start = 56.dp))
                }
            }
        }
    }
}

@Composable
private fun VerticalDividerSection() {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.spaceMedium)) {
        Text(
            "VerticalDivider separa itens em layouts horizontais.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = Dimens.elevationLow,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(horizontal = Dimens.spaceMedium, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(Dimens.spaceMedium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Seção A", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
                VerticalDivider(modifier = Modifier.fillMaxHeight())
                Text("Seção B", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
                VerticalDivider(modifier = Modifier.fillMaxHeight())
                Text("Seção C", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
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

@Preview(name = "Light", showBackground = true)
@Composable
private fun DividerLightPreview() {
    Material3DesignTheme { DividerScreen() }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun DividerDarkPreview() {
    Material3DesignTheme(darkTheme = true) { DividerScreen() }
}
