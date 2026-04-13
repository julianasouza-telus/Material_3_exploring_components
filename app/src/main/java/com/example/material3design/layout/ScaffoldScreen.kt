package com.example.material3design.layout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.ui.theme.Material3DesignTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var selectedTab by remember { mutableIntStateOf(0) }

    val navItems = listOf(
        Icons.Default.Home to "Início",
        Icons.Default.Favorite to "Favoritos",
        Icons.Default.Person to "Perfil"
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Scaffold Showcase") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, (icon, label) ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = { Icon(icon, contentDescription = null) },
                        label = { Text(label) }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch { snackbarHostState.showSnackbar("FAB clicado!") }
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text("Scaffold", style = MaterialTheme.typography.headlineMedium)

            Text(
                "Scaffold implementa o layout básico da estrutura visual do Material 3, " +
                        "fornecendo slots para os principais componentes de UI.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SectionTitle("Slots disponíveis")

            listOf(
                "topBar" to "TopAppBar, CenterAlignedTopAppBar, MediumTopAppBar, LargeTopAppBar",
                "bottomBar" to "NavigationBar",
                "floatingActionButton" to "FloatingActionButton, ExtendedFloatingActionButton",
                "snackbarHost" to "SnackbarHost(hostState)",
                "content" to "Conteúdo principal — recebe PaddingValues via lambda"
            ).forEach { (slot, description) ->
                SlotRow(slot = slot, description = description)
            }

            SectionTitle("FAB Position")
            Text(
                "Por padrão o FAB é posicionado no canto inferior direito (End). " +
                        "Toque no FAB para acionar uma Snackbar.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SectionTitle("Content padding")
            Text(
                "O lambda content recebe PaddingValues que incluem o espaço do topBar, " +
                        "bottomBar e snackbarHost. Use Modifier.padding(it) para evitar sobreposição.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/* ---------- COMPONENTS ---------- */

@Composable
private fun SlotRow(slot: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(slot, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            Text(description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
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
private fun ScaffoldLightPreview() {
    Material3DesignTheme { ScaffoldScreen() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Dark", showBackground = true)
@Composable
private fun ScaffoldDarkPreview() {
    Material3DesignTheme(darkTheme = true) { ScaffoldScreen() }
}
