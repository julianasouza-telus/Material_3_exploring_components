package com.example.material3design.navigation

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text("Tabs", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("Primary Tabs — Texto")
        PrimaryTextTabsSection()

        SectionTitle("Primary Tabs — Ícone + Texto")
        PrimaryIconTabsSection()

        SectionTitle("Secondary Tabs")
        SecondaryTabsSection()

        SectionTitle("Scrollable Tabs")
        ScrollableTabsSection()
    }
}

/* ---------- PRIMARY TEXT ---------- */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PrimaryTextTabsSection() {
    var selected by remember { mutableIntStateOf(0) }
    val tabs = listOf("Todos", "Ativos", "Arquivados")

    Column {
        PrimaryTabRow(selectedTabIndex = selected) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selected == index,
                    onClick = { selected = index },
                    text = { Text(title) }
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text("Conteúdo: ${tabs[selected]}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

/* ---------- PRIMARY ICON ---------- */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PrimaryIconTabsSection() {
    var selected by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        "Músicas" to Icons.Default.Favorite,
        "Álbuns" to Icons.Default.Home,
        "Artistas" to Icons.Default.Person,
        "Playlists" to Icons.Default.Menu,
    )

    Column {
        PrimaryTabRow(selectedTabIndex = selected) {
            tabs.forEachIndexed { index, (title, icon) ->
                Tab(
                    selected = selected == index,
                    onClick = { selected = index },
                    text = { Text(title) },
                    icon = { Icon(icon, contentDescription = title) }
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text("Conteúdo: ${tabs[selected].first}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

/* ---------- SECONDARY ---------- */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SecondaryTabsSection() {
    var selected by remember { mutableIntStateOf(0) }
    val tabs = listOf("Dia", "Semana", "Mês", "Ano")

    Column {
        SecondaryTabRow(selectedTabIndex = selected) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selected == index,
                    onClick = { selected = index },
                    text = { Text(title) }
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text("Período: ${tabs[selected]}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

/* ---------- SCROLLABLE ---------- */

@Composable
private fun ScrollableTabsSection() {
    var selected by remember { mutableIntStateOf(0) }
    val tabs = listOf("Tecnologia", "Design", "Negócios", "Saúde", "Esportes", "Ciência", "Arte")

    Column {
        ScrollableTabRow(
            selectedTabIndex = selected,
            edgePadding = 0.dp
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selected == index,
                    onClick = { selected = index },
                    text = { Text(title) }
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text("Categoria: ${tabs[selected]}", style = MaterialTheme.typography.bodyMedium)
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
private fun TabsLightPreview() {
    Material3DesignTheme {
        TabsScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Dark", showBackground = true)
@Composable
private fun TabsDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        TabsScreen()
    }
}
