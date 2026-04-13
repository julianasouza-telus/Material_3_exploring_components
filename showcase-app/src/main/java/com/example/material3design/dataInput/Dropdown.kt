package com.example.material3design.dataInput

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
import com.example.material3design.core.ui.theme.Dimens
import com.example.material3design.core.ui.theme.Material3DesignTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Dimens.spaceMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceLarge)
    ) {
        Text("Dropdown / Menus", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("ExposedDropdownMenuBox — Filled")
        ExposedDropdownFilledSection()

        SectionTitle("ExposedDropdownMenuBox — Outlined")
        ExposedDropdownOutlinedSection()

        SectionTitle("DropdownMenu (contextual)")
        FreeDropdownSection()
    }
}

/* ---------- SECTIONS ---------- */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExposedDropdownFilledSection() {
    val options = listOf("Opção 1", "Opção 2", "Opção 3", "Opção 4", "Opção 5")
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        TextField(
            value = selected,
            onValueChange = {},
            readOnly = true,
            label = { Text("Selecionar") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selected = option
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExposedDropdownOutlinedSection() {
    val months = listOf(
        "Janeiro", "Fevereiro", "Março", "Abril",
        "Maio", "Junho", "Julho", "Agosto",
        "Setembro", "Outubro", "Novembro", "Dezembro"
    )
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(months[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            readOnly = true,
            label = { Text("Mês") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            months.forEach { month ->
                DropdownMenuItem(
                    text = { Text(month) },
                    onClick = {
                        selected = month
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Composable
private fun FreeDropdownSection() {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = null)
            Spacer(Modifier.width(Dimens.spaceXSmall))
            Text("Mais opções")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Editar") },
                onClick = { expanded = false },
                leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text("Compartilhar") },
                onClick = { expanded = false },
                leadingIcon = { Icon(Icons.Default.Share, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text("Favoritar") },
                onClick = { expanded = false },
                leadingIcon = { Icon(Icons.Default.Favorite, contentDescription = null) }
            )
            HorizontalDivider()
            DropdownMenuItem(
                text = { Text("Excluir", color = MaterialTheme.colorScheme.error) },
                onClick = { expanded = false },
                leadingIcon = {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                }
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
private fun DropdownLightPreview() {
    Material3DesignTheme { DropdownScreen() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Dark", showBackground = true)
@Composable
private fun DropdownDarkPreview() {
    Material3DesignTheme(darkTheme = true) { DropdownScreen() }
}
