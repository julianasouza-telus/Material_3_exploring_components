//package com.example.material3showcase.ui.containment.lists
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun ListsScreen() {
//    var selectedIndex by remember { mutableStateOf<Int?>(null) }
//    var loading by remember { mutableStateOf(false) }
//    val items = remember {
//        List(10) { index -> "Item ${index + 1}" }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(32.dp)
//    ) {
//        Text("Lists", style = MaterialTheme.typography.headlineMedium)
//
//        SectionTitle("One-line List")
//        OneLineList(items)
//
//        SectionTitle("Two-line List")
//        TwoLineList(items)
//
//        SectionTitle("Three-line List")
//        ThreeLineList(items)
//
//        SectionTitle("Lists with Leading Icons & Avatars")
//        LeadingContentList(items)
//
//        SectionTitle("Lists with Trailing Actions")
//        TrailingActionsList(items)
//
//        SectionTitle("Selectable Lists")
//        SelectableList(items, selectedIndex, onSelect = { selectedIndex = it })
//
//        SectionTitle("Dividers & Sections")
//        DividedList(items)
//
//        SectionTitle("Loading & Empty States")
//        LoadingAndEmptyList(loading, onToggle = { loading = !loading })
//
//        SectionTitle("Horizontal List")
//        HorizontalList(items)
//    }
//}
//
///* ---------- ONE-LINE ---------- */
//
//@Composable
//private fun OneLineList(items: List<String>) {
//    LazyColumn {
//        items(items) { item ->
//            ListItem(
//                headlineText = { Text(item) }
//            )
//        }
//    }
//}
//
///* ---------- TWO-LINE ---------- */
//
//@Composable
//private fun TwoLineList(items: List<String>) {
//    LazyColumn {
//        items(items) { item ->
//            ListItem(
//                headlineText = { Text(item) },
//                supportingText = { Text("Supporting text for $item") }
//            )
//        }
//    }
//}
//
///* ---------- THREE-LINE ---------- */
//
//@Composable
//private fun ThreeLineList(items: List<String>) {
//    LazyColumn {
//        items(items) { item ->
//            ListItem(
//                headlineText = { Text(item) },
//                supportingText = {
//                    Text("Supporting text for $item with more detail and explanation.")
//                },
//                overlineText = { Text("Overline") }
//            )
//        }
//    }
//}
//
///* ---------- LEADING CONTENT ---------- */
//
//@Composable
//private fun LeadingContentList(items: List<String>) {
//    LazyColumn {
//        items(items) { item ->
//            ListItem(
//                headlineText = { Text(item) },
//                supportingText = { Text("With icon or avatar") },
//                leadingContent = {
//                    Icon(
//                        imageVector = Icons.Default.Person,
//                        contentDescription = null
//                    )
//                }
//            )
//        }
//    }
//}
//
///* ---------- TRAILING ACTIONS ---------- */
//
//@Composable
//private fun TrailingActionsList(items: List<String>) {
//    LazyColumn {
//        items(items) { item ->
//            ListItem(
//                headlineText = { Text(item) },
//                supportingText = { Text("Trailing actions") },
//                trailingContent = {
//                    Row {
//                        IconButton(onClick = {}) {
//                            Icon(Icons.Default.Edit, contentDescription = null)
//                        }
//                        IconButton(onClick = {}) {
//                            Icon(Icons.Default.Delete, contentDescription = null)
//                        }
//                    }
//                }
//            )
//        }
//    }
//}
//
///* ---------- SELECTABLE ---------- */
//
//@Composable
//private fun SelectableList(
//    items: List<String>,
//    selectedIndex: Int?,
//    onSelect: (Int) -> Unit
//) {
//    LazyColumn {
//        itemsIndexed(items) { index, item ->
//            val selected = selectedIndex == index
//            ListItem(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable { onSelect(index) },
//                headlineText = { Text(item) },
//                supportingText = { Text(if (selected) "Selected" else "Tap to select") },
//                leadingContent = {
//                    if (selected) {
//                        Icon(Icons.Default.Check, contentDescription = null)
//                    } else {
//                        Icon(Icons.Default.RadioButtonUnchecked, contentDescription = null)
//                    }
//                },
//                colors = if (selected)
//                    ListItemDefaults.colors(
//                        containerColor = MaterialTheme.colorScheme.primaryContainer
//                    )
//                else
//                    ListItemDefaults.colors()
//            )
//        }
//    }
//}
//
///* ---------- DIVIDERS & SECTIONS ---------- */
//
//@Composable
//private fun DividedList(items: List<String>) {
//    LazyColumn {
//        item {
//            Text(
//                "Section A",
//                modifier = Modifier.padding(16.dp),
//                style = MaterialTheme.typography.titleSmall
//            )
//        }
//
//        items(items.take(5)) { item ->
//            ListItem(
//                headlineText = { Text(item) },
//                supportingText = { Text("In section A") }
//            )
//            Divider()
//        }
//
//        item {
//            Text(
//                "Section B",
//                modifier = Modifier.padding(16.dp),
//                style = MaterialTheme.typography.titleSmall
//            )
//        }
//
//        items(items.drop(5)) { item ->
//            ListItem(
//                headlineText = { Text(item) },
//                supportingText = { Text("In section B") }
//            )
//            Divider()
//        }
//    }
//}
//
///* ---------- LOADING & EMPTY ---------- */
//
//@Composable
//private fun LoadingAndEmptyList(loading: Boolean, onToggle: () -> Unit) {
//    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
//        Button(onClick = onToggle) {
//            Text(if (loading) "Show Content" else "Show Loading")
//        }
//
//        if (loading) {
//            repeat(5) {
//                ListItem(
//                    headlineText = { Text("Loading…") },
//                    supportingText = { Text("Please wait") },
//                    leadingContent = {
//                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
//                    }
//                )
//                Divider()
//            }
//        } else {
//            LazyColumn {
//                items(3) { index ->
//                    ListItem(
//                        headlineText = { Text("Item ${index + 1}") },
//                        supportingText = { Text("Loaded content") }
//                    )
//                    Divider()
//                }
//            }
//        }
//    }
//}
//
///* ---------- HORIZONTAL LIST ---------- */
//
//@Composable
//private fun HorizontalList(items: List<String>) {
//    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
//        items(items) { item ->
//            Card(
//                modifier = Modifier
//                    .width(160.dp)
//                    .height(80.dp)
//            ) {
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    Text(item)
//                }
//            }
//        }
//    }
//}
//
///* ---------- UTILS ---------- */
//
//@Composable
//private fun SectionTitle(title: String) {
//    Text(text = title, style = MaterialTheme.typography.titleMedium)
//}
//
///* ---------- PREVIEWS ---------- */
//
//@Preview(showBackground = true)
//@Composable
//private fun ListsScreenPreview() {
//    MaterialTheme {
//        ListsScreen()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//private fun ListsDarkPreview() {
//    MaterialTheme(colorScheme = darkColorScheme()) {
//        ListsScreen()
//    }
//}
