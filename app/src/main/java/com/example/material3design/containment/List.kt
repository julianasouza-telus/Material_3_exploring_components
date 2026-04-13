package com.example.material3design.containment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.ui.theme.Material3DesignTheme

@Composable
fun ListsScreen() {
    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text("Lists", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("One-line Items")
        OneLineSection()

        SectionTitle("Two-line Items")
        TwoLineSection()

        SectionTitle("Three-line Items")
        ThreeLineSection()

        SectionTitle("Leading Content")
        LeadingContentSection()

        SectionTitle("Trailing Actions")
        TrailingActionsSection()

        SectionTitle("Selectable Items")
        SelectableSection(selectedIndex = selectedIndex, onSelect = { selectedIndex = it })

        SectionTitle("With Dividers")
        DividedSection()
    }
}

/* ---------- ONE-LINE ---------- */

@Composable
private fun OneLineSection() {
    Column {
        listOf("Inbox", "Sent", "Drafts").forEach { label ->
            ListItem(
                headlineContent = { Text(label) }
            )
        }
    }
}

/* ---------- TWO-LINE ---------- */

@Composable
private fun TwoLineSection() {
    Column {
        listOf(
            "Design Review" to "Today at 10:00 AM",
            "Sprint Planning" to "Tomorrow at 9:00 AM",
            "Retrospective" to "Friday at 3:00 PM"
        ).forEach { (title, subtitle) ->
            ListItem(
                headlineContent = { Text(title) },
                supportingContent = { Text(subtitle) }
            )
        }
    }
}

/* ---------- THREE-LINE ---------- */

@Composable
private fun ThreeLineSection() {
    Column {
        listOf(
            Triple("João Silva", "Hey! Are you available for a quick call?", "2 min ago"),
            Triple("Ana Costa", "The report is ready for your review.", "1 hr ago"),
        ).forEach { (name, message, time) ->
            ListItem(
                overlineContent = { Text(time, style = MaterialTheme.typography.labelSmall) },
                headlineContent = { Text(name) },
                supportingContent = { Text(message, maxLines = 1) }
            )
        }
    }
}

/* ---------- LEADING CONTENT ---------- */

@Composable
private fun LeadingContentSection() {
    Column {
        ListItem(
            headlineContent = { Text("With Icon") },
            supportingContent = { Text("Leading icon example") },
            leadingContent = {
                Icon(Icons.Default.Notifications, contentDescription = null)
            }
        )
        ListItem(
            headlineContent = { Text("With Avatar") },
            supportingContent = { Text("Leading avatar placeholder") },
            leadingContent = {
                Surface(
                    modifier = Modifier.size(40.dp).clip(CircleShape),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("A", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        )
        ListItem(
            headlineContent = { Text("With Checkbox") },
            leadingContent = {
                Checkbox(checked = true, onCheckedChange = null)
            }
        )
    }
}

/* ---------- TRAILING ACTIONS ---------- */

@Composable
private fun TrailingActionsSection() {
    Column {
        ListItem(
            headlineContent = { Text("Item with Edit & Delete") },
            supportingContent = { Text("Trailing action buttons") },
            trailingContent = {
                Row {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        )
        ListItem(
            headlineContent = { Text("Item with Switch") },
            trailingContent = {
                Switch(checked = true, onCheckedChange = null)
            }
        )
        ListItem(
            headlineContent = { Text("Item with Chevron") },
            supportingContent = { Text("Navigates to detail") },
            trailingContent = {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
            }
        )
    }
}

/* ---------- SELECTABLE ---------- */

@Composable
private fun SelectableSection(selectedIndex: Int?, onSelect: (Int) -> Unit) {
    val items = listOf("Option Alpha", "Option Beta", "Option Gamma")
    Column {
        items.forEachIndexed { index, item ->
            val isSelected = selectedIndex == index
            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelect(index) },
                headlineContent = { Text(item) },
                supportingContent = { Text(if (isSelected) "Selected" else "Tap to select") },
                leadingContent = {
                    RadioButton(
                        selected = isSelected,
                        onClick = { onSelect(index) }
                    )
                },
                colors = if (isSelected)
                    ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                else
                    ListItemDefaults.colors()
            )
        }
    }
}

/* ---------- DIVIDED ---------- */

@Composable
private fun DividedSection() {
    Column {
        Text(
            "Section A",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
        listOf("Alpha", "Beta").forEach { item ->
            ListItem(headlineContent = { Text(item) })
            HorizontalDivider()
        }
        Text(
            "Section B",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
        listOf("Gamma", "Delta").forEach { item ->
            ListItem(headlineContent = { Text(item) })
            HorizontalDivider()
        }
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
private fun ListsScreenLightPreview() {
    Material3DesignTheme {
        ListsScreen()
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun ListsScreenDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        ListsScreen()
    }
}
