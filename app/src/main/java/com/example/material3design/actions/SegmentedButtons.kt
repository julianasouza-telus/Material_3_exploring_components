package com.example.material3design.actions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.ui.theme.Material3DesignTheme

@Composable
fun SegmentedButtonsShowcase() {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {

        Text("Segmented Buttons", style = MaterialTheme.typography.titleLarge)

        // ----------------------------
        // Single Choice (Text)
        // ----------------------------
        SectionTitle("Single Choice — Text")
        var singleSelected by remember { mutableIntStateOf(0) }
        SingleChoiceSegmentedButtonRow {
            listOf("One", "Two", "Three").forEachIndexed { index, label ->
                SegmentedButton(
                    selected = singleSelected == index,
                    onClick = { singleSelected = index },
                    shape = SegmentedButtonDefaults.itemShape(index, 3)
                ) {
                    Text(label)
                }
            }
        }

        // ----------------------------
        // Single Choice (Icons + Text)
        // ----------------------------
        SectionTitle("Single Choice — Icons")
        var iconSingleSelected by remember { mutableIntStateOf(0) }
        SingleChoiceSegmentedButtonRow {
            listOf("Home", "Fav", "Settings").forEachIndexed { index, label ->
                SegmentedButton(
                    selected = iconSingleSelected == index,
                    onClick = { iconSingleSelected = index },
                    shape = SegmentedButtonDefaults.itemShape(index, 3),
                    icon = {
                        when (index) {
                            0 -> Icon(Icons.Default.Home, contentDescription = null)
                            1 -> Icon(Icons.Default.Favorite, contentDescription = null)
                            else -> Icon(Icons.Default.Settings, contentDescription = null)
                        }
                    }
                ) {
                    Text(label)
                }
            }
        }

        // ----------------------------
        // Multi Choice
        // ----------------------------
        SectionTitle("Multi Choice")
        val multiSelected = remember { mutableStateListOf(false, true, false) }
        MultiChoiceSegmentedButtonRow {
            listOf("A", "B", "C").forEachIndexed { index, label ->
                SegmentedButton(
                    checked = multiSelected[index],
                    onCheckedChange = { multiSelected[index] = it },
                    shape = SegmentedButtonDefaults.itemShape(index, 3)
                ) {
                    Text(label)
                }
            }
        }

        // ----------------------------
        // Disabled States
        // ----------------------------
        SectionTitle("Disabled States")
        SingleChoiceSegmentedButtonRow {
            listOf("One", "Two", "Three").forEachIndexed { index, label ->
                SegmentedButton(
                    selected = index == 1,
                    onClick = {},
                    enabled = false,
                    shape = SegmentedButtonDefaults.itemShape(index, 3)
                ) {
                    Text(label)
                }
            }
        }

        // ----------------------------
        // Custom Shapes & Colors
        // ----------------------------
        SectionTitle("Custom Shapes & Colors")
        var customSelected by remember { mutableIntStateOf(0) }
        SingleChoiceSegmentedButtonRow {
            listOf("Round", "Soft", "Sharp").forEachIndexed { index, label ->
                SegmentedButton(
                    selected = customSelected == index,
                    onClick = { customSelected = index },
                    shape = when (index) {
                        0 -> RoundedCornerShape(50)
                        1 -> MaterialTheme.shapes.medium
                        else -> MaterialTheme.shapes.small
                    }
                ) {
                    Text(label)
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleMedium)
}


@Preview(name = "Light", showBackground = true)
@Composable
private fun SegmentedButtonsLightPreview() {
    Material3DesignTheme {
        SegmentedButtonsShowcase()
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun SegmentedButtonsDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        SegmentedButtonsShowcase()
    }
}
