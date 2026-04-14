package com.example.material3design.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Dimens
import com.example.material3design.core.ui.theme.Material3DesignTheme

@Composable
fun LazyLayoutsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Dimens.spaceMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceXLarge)
    ) {
        Text("Lazy Layouts", style = MaterialTheme.typography.headlineMedium)

        SectionHeader("LazyColumn — Lista vertical")
        LazyColumnSection()

        SectionHeader("LazyColumn — Com cabeçalhos (sticky)")
        StickyHeaderSection()

        SectionHeader("LazyRow — Scroll horizontal")
        LazyRowSection()

        SectionHeader("LazyRow — Cards horizontais")
        LazyRowCardsSection()
    }
}

/* ─── LAZY COLUMN ─────────────────────────────────────────────────────────── */

@Composable
private fun LazyColumnSection() {
    val items = (1..8).map { "Item $it" }
    Card(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            contentPadding = PaddingValues(Dimens.spaceSmall),
            verticalArrangement = Arrangement.spacedBy(Dimens.spaceXSmall)
        ) {
            items(items) { label ->
                ListItem(
                    headlineContent = { Text(label) },
                    leadingContent = {
                        Icon(Icons.Default.Folder, contentDescription = null)
                    }
                )
                HorizontalDivider()
            }
        }
    }
}

/* ─── LAZY COLUMN STICKY HEADERS ─────────────────────────────────────────── */

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun StickyHeaderSection() {
    val sections = mapOf(
        "Hoje" to listOf("Reunião de design", "Code review", "Daily"),
        "Amanhã" to listOf("Sprint planning", "Deploy"),
        "Esta semana" to listOf("Retrospectiva", "Onboarding", "Docs")
    )
    Card(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            contentPadding = PaddingValues(vertical = Dimens.spaceXSmall)
        ) {
            sections.forEach { (header, items) ->
                stickyHeader {
                    Surface(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = header,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(
                                horizontal = Dimens.spaceMedium,
                                vertical = Dimens.spaceXSmall
                            )
                        )
                    }
                }
                items(items) { task ->
                    ListItem(
                        headlineContent = { Text(task) },
                        leadingContent = {
                            Icon(
                                Icons.Default.Star,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    )
                }
            }
        }
    }
}

/* ─── LAZY ROW BÁSICO ────────────────────────────────────────────────────── */

@Composable
private fun LazyRowSection() {
    val chips = listOf("Design", "Android", "Compose", "Material 3", "Kotlin", "UI", "Animations", "State")
    LazyRow(
        contentPadding = PaddingValues(horizontal = Dimens.spaceXSmall),
        horizontalArrangement = Arrangement.spacedBy(Dimens.spaceSmall)
    ) {
        items(chips) { label ->
            AssistChip(
                onClick = {},
                label = { Text(label) }
            )
        }
    }
}

/* ─── LAZY ROW CARDS ─────────────────────────────────────────────────────── */

private data class MediaItem(val title: String, val subtitle: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@Composable
private fun LazyRowCardsSection() {
    val items = listOf(
        MediaItem("Fotos", "128 itens", Icons.Default.Image),
        MediaItem("Notificações", "3 novas", Icons.Default.Notifications),
        MediaItem("Arquivos", "45 itens", Icons.Default.Folder),
        MediaItem("Favoritos", "12 itens", Icons.Default.Star),
        MediaItem("Downloads", "8 itens", Icons.Default.Folder),
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = Dimens.spaceXSmall),
        horizontalArrangement = Arrangement.spacedBy(Dimens.spaceSmall)
    ) {
        items(items) { item ->
            ElevatedCard(modifier = Modifier.width(160.dp)) {
                Column(
                    modifier = Modifier.padding(Dimens.spaceMedium),
                    verticalArrangement = Arrangement.spacedBy(Dimens.spaceSmall)
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        modifier = Modifier.size(Dimens.iconLarge),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(item.title, style = MaterialTheme.typography.titleSmall)
                    Text(
                        item.subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

/* ─── HELPERS ────────────────────────────────────────────────────────────── */

@Composable
private fun SectionHeader(title: String) {
    Text(title, style = MaterialTheme.typography.titleMedium)
}

/* ─── PREVIEWS ───────────────────────────────────────────────────────────── */

@Preview(name = "LazyLayouts — Light", showBackground = true, heightDp = 900)
@Composable
private fun LazyLayoutsLightPreview() {
    Material3DesignTheme {
        LazyLayoutsScreen()
    }
}

@Preview(name = "LazyLayouts — Dark", showBackground = true, backgroundColor = 0xFF1C1B1F, heightDp = 900)
@Composable
private fun LazyLayoutsDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        LazyLayoutsScreen()
    }
}
