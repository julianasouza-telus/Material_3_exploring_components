package com.example.material3design.dataInput

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Dimens
import com.example.material3design.core.ui.theme.Material3DesignTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarScreen() {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var dockedQuery by remember { mutableStateOf("") }
    var dockedActive by remember { mutableStateOf(false) }

    val suggestions = listOf(
        "Kotlin", "Compose", "Material 3", "Android", "Jetpack",
        "State", "ViewModel", "Navigation", "Hilt", "Room"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Scrollable body (below the search bar)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    top = 80.dp,
                    start = Dimens.spaceMedium,
                    end = Dimens.spaceMedium,
                    bottom = Dimens.spaceMedium
                ),
            verticalArrangement = Arrangement.spacedBy(Dimens.spaceXLarge)
        ) {
            Text("Search Bar", style = MaterialTheme.typography.headlineMedium)

            Text(
                "SearchBar: expande para tela inteira exibindo sugestões e resultados. " +
                        "Toque na barra acima para ativar.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SectionTitle("DockedSearchBar")
            Text(
                "Expandível inline; mantém o restante do layout visível ao redor.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            DockedSearchBarSection(
                query = dockedQuery,
                active = dockedActive,
                onQueryChange = { dockedQuery = it },
                onActiveChange = { dockedActive = it },
                suggestions = suggestions
            )
        }

        // Full-width SearchBar pinned at the top
        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = { query = it },
                    onSearch = { active = false },
                    expanded = active,
                    onExpandedChange = { active = it },
                    placeholder = { Text("Pesquisar...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = if (query.isNotEmpty()) {
                        {
                            IconButton(onClick = { query = "" }) {
                                Icon(Icons.Default.Close, contentDescription = "Limpar")
                            }
                        }
                    } else null
                )
            },
            expanded = active,
            onExpandedChange = { active = it },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(horizontal = if (active) 0.dp else Dimens.spaceMedium)
        ) {
            val filtered = suggestions.filter { it.contains(query, ignoreCase = true) }
            if (filtered.isEmpty()) {
                Text(
                    "Nenhum resultado para \"$query\"",
                    modifier = Modifier.padding(Dimens.spaceMedium),
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                filtered.forEach { item ->
                    ListItem(
                        headlineContent = { Text(item) },
                        leadingContent = {
                            Icon(Icons.Default.Search, contentDescription = null)
                        },
                        modifier = Modifier.clickable {
                            query = item
                            active = false
                        }
                    )
                }
            }
        }
    }
}

/* ---------- DOCKED SEARCH BAR ---------- */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DockedSearchBarSection(
    query: String,
    active: Boolean,
    onQueryChange: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    suggestions: List<String>
) {
    val filtered = suggestions.filter { it.contains(query, ignoreCase = true) }

    DockedSearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = { onActiveChange(false) },
                expanded = active,
                onExpandedChange = onActiveChange,
                placeholder = { Text("Pesquisar tópico...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = if (query.isNotEmpty()) {
                    {
                        IconButton(onClick = { onQueryChange("") }) {
                            Icon(Icons.Default.Close, contentDescription = "Limpar")
                        }
                    }
                } else null
            )
        },
        expanded = active,
        onExpandedChange = onActiveChange,
        modifier = Modifier.fillMaxWidth()
    ) {
        filtered.forEach { item ->
            ListItem(
                headlineContent = { Text(item) },
                modifier = Modifier.clickable {
                    onQueryChange(item)
                    onActiveChange(false)
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
private fun SearchBarLightPreview() {
    Material3DesignTheme { SearchBarScreen() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Dark", showBackground = true)
@Composable
private fun SearchBarDarkPreview() {
    Material3DesignTheme(darkTheme = true) { SearchBarScreen() }
}
