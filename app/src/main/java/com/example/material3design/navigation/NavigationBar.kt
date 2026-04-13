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
import com.example.material3design.ui.theme.Material3DesignTheme

@Composable
fun NavigationBarScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text("Navigation Bar", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("Básico — com rótulo")
        BasicNavigationBar()

        SectionTitle("Com Badges")
        BadgedNavigationBar()

        SectionTitle("Sem Rótulo")
        NoLabelNavigationBar()

        SectionTitle("Com item desabilitado")
        DisabledItemNavigationBar()
    }
}

/* ---------- BASIC ---------- */

@Composable
private fun BasicNavigationBar() {
    var selected by remember { mutableIntStateOf(0) }
    val items = listOf(
        "Início" to Icons.Default.Home,
        "Buscar" to Icons.Default.Search,
        "Favoritos" to Icons.Default.Favorite,
        "Perfil" to Icons.Default.Person,
    )
    NavigationBar {
        items.forEachIndexed { index, (label, icon) ->
            NavigationBarItem(
                selected = selected == index,
                onClick = { selected = index },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) }
            )
        }
    }
}

/* ---------- WITH BADGES ---------- */

@Composable
private fun BadgedNavigationBar() {
    var selected by remember { mutableIntStateOf(0) }
    NavigationBar {
        NavigationBarItem(
            selected = selected == 0,
            onClick = { selected = 0 },
            icon = { Icon(Icons.Default.Home, contentDescription = "Início") },
            label = { Text("Início") }
        )
        NavigationBarItem(
            selected = selected == 1,
            onClick = { selected = 1 },
            icon = {
                BadgedBox(badge = { Badge { Text("3") } }) {
                    Icon(Icons.Default.Notifications, contentDescription = "Alertas")
                }
            },
            label = { Text("Alertas") }
        )
        NavigationBarItem(
            selected = selected == 2,
            onClick = { selected = 2 },
            icon = {
                BadgedBox(badge = { Badge { Text("99+") } }) {
                    Icon(Icons.Default.Email, contentDescription = "Mensagens")
                }
            },
            label = { Text("Mensagens") }
        )
        NavigationBarItem(
            selected = selected == 3,
            onClick = { selected = 3 },
            icon = {
                BadgedBox(badge = { Badge() }) {
                    Icon(Icons.Default.Settings, contentDescription = "Config")
                }
            },
            label = { Text("Config") }
        )
    }
}

/* ---------- NO LABEL ---------- */

@Composable
private fun NoLabelNavigationBar() {
    var selected by remember { mutableIntStateOf(0) }
    val icons = listOf(Icons.Default.Home, Icons.Default.Search, Icons.Default.Favorite, Icons.Default.Person)
    NavigationBar {
        icons.forEachIndexed { index, icon ->
            NavigationBarItem(
                selected = selected == index,
                onClick = { selected = index },
                icon = { Icon(icon, contentDescription = null) }
            )
        }
    }
}

/* ---------- DISABLED ITEM ---------- */

@Composable
private fun DisabledItemNavigationBar() {
    var selected by remember { mutableIntStateOf(0) }
    NavigationBar {
        NavigationBarItem(
            selected = selected == 0,
            onClick = { selected = 0 },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Início") }
        )
        NavigationBarItem(
            selected = selected == 1,
            onClick = { selected = 1 },
            icon = { Icon(Icons.Default.Search, contentDescription = null) },
            label = { Text("Buscar") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            enabled = false,
            icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
            label = { Text("Favoritos") }
        )
        NavigationBarItem(
            selected = selected == 3,
            onClick = { selected = 3 },
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text("Perfil") }
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
private fun NavigationBarLightPreview() {
    Material3DesignTheme {
        NavigationBarScreen()
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun NavigationBarDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        NavigationBarScreen()
    }
}
