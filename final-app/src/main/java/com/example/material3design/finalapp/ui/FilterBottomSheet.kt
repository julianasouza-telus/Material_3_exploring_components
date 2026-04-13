package com.example.material3design.finalapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Material3DesignTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    currentFilter: String,
    onFilterSelect: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            Text(
                text = "Filter Tasks",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            FilterOption(
                label = "All Tasks",
                selected = currentFilter == "all",
                onClick = { onFilterSelect("all") }
            )
            FilterOption(
                label = "Important Only",
                selected = currentFilter == "important",
                onClick = { onFilterSelect("important") }
            )
            FilterOption(
                label = "Completed",
                selected = currentFilter == "completed",
                onClick = { onFilterSelect("completed") }
            )
        }
    }
}

@Composable
private fun FilterOption(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
        RadioButton(
            selected = selected,
            onClick = onClick
        )
    }
}

@Preview(name = "FilterBottomSheet Content — Light", showBackground = true)
@Composable
private fun FilterBottomSheetContentLightPreview() {
    Material3DesignTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                Text(
                    text = "Filter Tasks",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                FilterOption(label = "All Tasks", selected = true, onClick = {})
                FilterOption(label = "Important Only", selected = false, onClick = {})
                FilterOption(label = "Completed", selected = false, onClick = {})
            }
        }
    }
}

@Preview(name = "FilterBottomSheet Content — Dark", showBackground = true, backgroundColor = 0xFF1C1B1F)
@Composable
private fun FilterBottomSheetContentDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                Text(
                    text = "Filter Tasks",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                FilterOption(label = "All Tasks", selected = false, onClick = {})
                FilterOption(label = "Important Only", selected = true, onClick = {})
                FilterOption(label = "Completed", selected = false, onClick = {})
            }
        }
    }
}