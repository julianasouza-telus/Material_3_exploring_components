package com.example.material3design.navigation

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
import com.example.material3design.core.ui.theme.Material3DesignTheme

@Composable
fun NavigationRailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text("Navigation Rail", style = MaterialTheme.typography.headlineMedium)

        Text(
            "Navigation Rail é ideal para telas médias (tablet/janela expandida). " +
                    "Aparece na lateral da interface.",
            style = MaterialTheme.typography.bodyMedium
        )

        SectionTitle("Rail Básico — com FAB no header")
        Row(modifier = Modifier.height(360.dp)) {
            BasicNavigationRail()
            VerticalDivider()
            Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("Conteúdo principal", style = MaterialTheme.typography.bodyLarge)
            }
        }

        SectionTitle("Rail sem Header")
        Row(modifier = Modifier.height(300.dp)) {
            NoHeaderNavigationRail()
            VerticalDivider()
            Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("Conteúdo principal", style = MaterialTheme.typography.bodyLarge)
            }
        }

        SectionTitle("Rail com Badges")
        Row(modifier = Modifier.height(300.dp)) {
            BadgedNavigationRail()
            VerticalDivider()
            Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("Conteúdo principal", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

/* ---------- BASIC WITH FAB HEADER ---------- */

@Composable
private fun BasicNavigationRail() {
    var selected by remember { mutableIntStateOf(0) }
    val items = listOf(
        "Início" to Icons.Default.Home,
        "Buscar" to Icons.Default.Search,
        "Favoritos" to Icons.Default.Favorite,
        "Perfil" to Icons.Default.Person,
    )
    NavigationRail(
        header = {
            FloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(Icons.Default.Add, contentDescription = "Criar")
            }
        }
    ) {
        items.forEachIndexed { index, (label, icon) ->
            NavigationRailItem(
                selected = selected == index,
                onClick = { selected = index },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) }
            )
        }
    }
}

/* ---------- NO HEADER ---------- */

@Composable
private fun NoHeaderNavigationRail() {
    var selected by remember { mutableIntStateOf(0) }
    val items = listOf(
        "Início" to Icons.Default.Home,
        "Buscar" to Icons.Default.Search,
        "Configurações" to Icons.Default.Settings,
    )
    NavigationRail {
        Spacer(Modifier.weight(1f))
        items.forEachIndexed { index, (label, icon) ->
            NavigationRailItem(
                selected = selected == index,
                onClick = { selected = index },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) }
            )
        }
        Spacer(Modifier.weight(1f))
    }
}

/* ---------- WITH BADGES ---------- */

@Composable
private fun BadgedNavigationRail() {
    var selected by remember { mutableIntStateOf(0) }
    NavigationRail {
        NavigationRailItem(
            selected = selected == 0,
            onClick = { selected = 0 },
            icon = { Icon(Icons.Default.Home, contentDescription = "Início") },
            label = { Text("Início") }
        )
        NavigationRailItem(
            selected = selected == 1,
            onClick = { selected = 1 },
            icon = {
                BadgedBox(badge = { Badge { Text("5") } }) {
                    Icon(Icons.Default.Notifications, contentDescription = "Alertas")
                }
            },
            label = { Text("Alertas") }
        )
        NavigationRailItem(
            selected = selected == 2,
            onClick = { selected = 2 },
            icon = {
                BadgedBox(badge = { Badge() }) {
                    Icon(Icons.Default.Email, contentDescription = "Mensagens")
                }
            },
            label = { Text("Mensagens") }
        )
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
private fun NavigationRailLightPreview() {
    Material3DesignTheme {
        Row(modifier = Modifier.height(400.dp)) {
            BasicNavigationRail()
            VerticalDivider()
            Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("Conteúdo principal")
            }
        }
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun NavigationRailDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        Row(modifier = Modifier.height(400.dp)) {
            BasicNavigationRail()
            VerticalDivider()
            Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("Conteúdo principal")
            }
        }
    }
}
