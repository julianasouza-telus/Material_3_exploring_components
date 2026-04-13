package com.example.material3design.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.ui.theme.Dimens
import com.example.material3design.ui.theme.Material3DesignTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Dimens.spaceMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceXLarge)
    ) {
        Text("Top App Bars", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("TopAppBar — Small")
        TopAppBar(
            title = { Text("Small Top App Bar") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            },
            actions = {
                IconButton(onClick = {}) { Icon(Icons.Default.Favorite, contentDescription = null) }
                IconButton(onClick = {}) { Icon(Icons.Default.Settings, contentDescription = null) }
                IconButton(onClick = {}) { Icon(Icons.Default.MoreVert, contentDescription = null) }
            }
        )

        SectionTitle("CenterAlignedTopAppBar")
        CenterAlignedTopAppBar(
            title = { Text("Título Centralizado") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            },
            actions = {
                IconButton(onClick = {}) { Icon(Icons.Default.Search, contentDescription = null) }
                IconButton(onClick = {}) { Icon(Icons.Default.MoreVert, contentDescription = null) }
            }
        )

        SectionTitle("MediumTopAppBar")
        MediumTopAppBar(
            title = { Text("Medium Top App Bar") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            },
            actions = {
                IconButton(onClick = {}) { Icon(Icons.Default.Share, contentDescription = null) }
                IconButton(onClick = {}) { Icon(Icons.Default.MoreVert, contentDescription = null) }
            }
        )

        SectionTitle("LargeTopAppBar")
        LargeTopAppBar(
            title = { Text("Large Top App Bar") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            },
            actions = {
                IconButton(onClick = {}) { Icon(Icons.Default.Search, contentDescription = null) }
                IconButton(onClick = {}) { Icon(Icons.Default.MoreVert, contentDescription = null) }
            }
        )

        SectionTitle("Com Scroll Behavior (EnterAlways)")
        Text(
            "Deslize a lista abaixo para ver a barra sumir e reaparecer.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        ScrollBehaviorExample()
    }
}

/* ---------- SCROLL BEHAVIOR DEMO ---------- */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScrollBehaviorExample() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text("Com scroll behavior") },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Menu, contentDescription = null)
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { padding ->
            LazyColumn(contentPadding = padding) {
                items(20) { i ->
                    ListItem(headlineContent = { Text("Item ${i + 1}") })
                    HorizontalDivider()
                }
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Light", showBackground = true)
@Composable
private fun TopAppBarsLightPreview() {
    Material3DesignTheme { TopAppBarsScreen() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Dark", showBackground = true)
@Composable
private fun TopAppBarsDarkPreview() {
    Material3DesignTheme(darkTheme = true) { TopAppBarsScreen() }
}
