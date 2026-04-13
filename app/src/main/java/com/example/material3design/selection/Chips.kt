package com.example.material3design.selection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.ui.theme.Material3DesignTheme

@Composable
fun ChipsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Chips", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("Assist Chips")
        AssistChipsSection()

        SectionTitle("Filter Chips")
        FilterChipsSection()

        SectionTitle("Input Chips")
        InputChipsSection()

        SectionTitle("Suggestion Chips")
        SuggestionChipsSection()

        SectionTitle("Elevated Variants")
        ElevatedVariantsSection()

        SectionTitle("Desabilitado")
        DisabledSection()
    }
}

/* ---------- ASSIST CHIPS ---------- */

@Composable
private fun AssistChipsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AssistChip(
                onClick = {},
                label = { Text("Adicionar ao calendário") },
                leadingIcon = { Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp)) }
            )
            AssistChip(
                onClick = {},
                label = { Text("Compartilhar") },
                leadingIcon = { Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(18.dp)) }
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AssistChip(onClick = {}, label = { Text("Sem ícone") })
            AssistChip(
                onClick = {},
                label = { Text("Editar") },
                leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(18.dp)) }
            )
        }
    }
}

/* ---------- FILTER CHIPS ---------- */

@Composable
private fun FilterChipsSection() {
    val filters = listOf("Todos", "Fotos", "Vídeos", "Documentos", "Favoritos")
    val selected = remember { mutableStateListOf(true, false, false, false, false) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            filters.take(3).forEachIndexed { index, label ->
                FilterChip(
                    selected = selected[index],
                    onClick = { selected[index] = !selected[index] },
                    label = { Text(label) }
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            filters.drop(3).forEachIndexed { index, label ->
                FilterChip(
                    selected = selected[index + 3],
                    onClick = { selected[index + 3] = !selected[index + 3] },
                    label = { Text(label) },
                    leadingIcon = if (selected[index + 3]) {
                        { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp)) }
                    } else null
                )
            }
        }
    }
}

/* ---------- INPUT CHIPS ---------- */

@Composable
private fun InputChipsSection() {
    val tags = remember { mutableStateListOf("Kotlin", "Compose", "Material3", "Android") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            "Toque no × para remover",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            tags.toList().forEach { tag ->
                InputChip(
                    selected = false,
                    onClick = {},
                    label = { Text(tag) },
                    trailingIcon = {
                        IconButton(
                            onClick = { tags.remove(tag) },
                            modifier = Modifier.size(18.dp)
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Remover $tag",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                )
            }
        }
        if (tags.isEmpty()) {
            AssistChip(
                onClick = { tags.addAll(listOf("Kotlin", "Compose", "Material3", "Android")) },
                label = { Text("Restaurar tags") },
                leadingIcon = { Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp)) }
            )
        }
    }
}

/* ---------- SUGGESTION CHIPS ---------- */

@Composable
private fun SuggestionChipsSection() {
    val suggestions = listOf("Amanhã às 9h", "Próxima semana", "Em um mês", "Sem data")

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            suggestions.take(2).forEach { label ->
                SuggestionChip(onClick = {}, label = { Text(label) })
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            suggestions.drop(2).forEach { label ->
                SuggestionChip(onClick = {}, label = { Text(label) })
            }
        }
    }
}

/* ---------- ELEVATED VARIANTS ---------- */

@Composable
private fun ElevatedVariantsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ElevatedAssistChip(
                onClick = {},
                label = { Text("Elevated Assist") },
                leadingIcon = { Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp)) }
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            var elevatedFilter by remember { mutableStateOf(false) }
            ElevatedFilterChip(
                selected = elevatedFilter,
                onClick = { elevatedFilter = !elevatedFilter },
                label = { Text("Elevated Filter") }
            )
            ElevatedSuggestionChip(
                onClick = {},
                label = { Text("Elevated Suggestion") }
            )
        }
    }
}

/* ---------- DISABLED ---------- */

@Composable
private fun DisabledSection() {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        AssistChip(onClick = {}, label = { Text("Assist") }, enabled = false)
        FilterChip(selected = false, onClick = {}, label = { Text("Filter") }, enabled = false)
        SuggestionChip(onClick = {}, label = { Text("Suggestion") }, enabled = false)
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
private fun ChipsLightPreview() {
    Material3DesignTheme {
        ChipsScreen()
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun ChipsDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        ChipsScreen()
    }
}
