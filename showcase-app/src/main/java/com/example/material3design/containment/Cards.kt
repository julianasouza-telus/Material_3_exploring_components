package com.example.material3design.containment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Material3DesignTheme

@Composable
fun CardsScreen() {
    var selected by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text("Cards", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("Filled Cards")
        FilledCardsSection(selected, loading)

        SectionTitle("Elevated Cards")
        ElevatedCardsSection()

        SectionTitle("Outlined Cards")
        OutlinedCardsSection()

        SectionTitle("Cards with Media")
        MediaCardsSection()

        SectionTitle("Selectable & Clickable Cards")
        SelectableCardsSection(selected, onSelect = { selected = !selected })

        SectionTitle("Loading / Skeleton Cards")
        LoadingCardsSection(loading, onToggle = { loading = !loading })
    }
}

/* ---------- FILLED CARDS ---------- */

@Composable
private fun FilledCardsSection(selected: Boolean, loading: Boolean) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            CardContent(
                title = "Basic Filled Card",
                body = "This is a simple filled card with text content."
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            CardContent(
                title = "Tonal Filled Card",
                body = "Using secondaryContainer color for tonal emphasis."
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            CardContent(
                title = "Disabled Filled Card",
                body = "This card is disabled and non-interactive."
            )
        }
    }
}

/* ---------- ELEVATED CARDS ---------- */

@Composable
private fun ElevatedCardsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            CardContent(
                title = "Basic Elevated Card",
                body = "This card uses elevation to stand out."
            )
        }

        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 12.dp)
        ) {
            CardContent(
                title = "High Elevation Card",
                body = "Custom elevation for more emphasis."
            )
        }
    }
}

/* ---------- OUTLINED CARDS ---------- */

@Composable
private fun OutlinedCardsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            CardContent(
                title = "Basic Outlined Card",
                body = "This card has a visible border instead of fill or elevation."
            )
        }

        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            CardContent(
                title = "Outlined with Tonal Background",
                body = "Outlined card combined with tonal background."
            )
        }
    }
}

/* ---------- MEDIA CARDS ---------- */

@Composable
private fun MediaCardsSection() {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(3) { index ->
            Card(
                modifier = Modifier
                    .width(220.dp)
                    .height(260.dp)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Media ${index + 1}")
                    }

                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Media Card", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "Card with image/media content and supporting text.",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(onClick = {}) {
                                Icon(Icons.Default.Favorite, contentDescription = null)
                            }
                            IconButton(onClick = {}) {
                                Icon(Icons.Default.Share, contentDescription = null)
                            }
                        }
                    }
                }
            }
        }
    }
}

/* ---------- SELECTABLE / CLICKABLE CARDS ---------- */

@Composable
private fun SelectableCardsSection(selected: Boolean, onSelect: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onSelect() },
            colors = if (selected)
                CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            else
                CardDefaults.cardColors()
        ) {
            CardContent(
                title = if (selected) "Selected Card" else "Selectable Card",
                body = "Tap to toggle selection state."
            )
        }

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onSelect() }
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Clickable Elevated Card", style = MaterialTheme.typography.titleMedium)
                    Text("This card responds to clicks.", style = MaterialTheme.typography.bodyMedium)
                }
                Icon(Icons.Default.PlayArrow, contentDescription = null)
            }
        }
    }
}

/* ---------- LOADING / SKELETON ---------- */

@Composable
private fun LoadingCardsSection(loading: Boolean, onToggle: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Button(onClick = onToggle) {
            Text(if (loading) "Hide Loading" else "Show Loading")
        }

        if (loading) {
            repeat(2) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        } else {
            Card(modifier = Modifier.fillMaxWidth()) {
                CardContent(
                    title = "Loaded Card",
                    body = "This card replaces the loading state."
                )
            }
        }
    }
}

/* ---------- REUSABLE CONTENT ---------- */

@Composable
private fun CardContent(title: String, body: String) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(title, style = MaterialTheme.typography.titleMedium)
        Text(body, style = MaterialTheme.typography.bodyMedium)
    }
}

/* ---------- UTILS ---------- */

@Composable
private fun SectionTitle(title: String) {
    Text(text = title, style = MaterialTheme.typography.titleMedium)
}

/* ---------- PREVIEWS ---------- */

@Preview(name = "Light", showBackground = true)
@Composable
private fun CardsScreenPreview() {
    Material3DesignTheme {
        CardsScreen()
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun CardsDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        CardsScreen()
    }
}
