package com.example.material3design.finalapp.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.material3design.core.ui.theme.Material3DesignTheme
import com.example.material3design.core.ui.theme.TaskFlowError

@Composable
fun DeleteConfirmDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Task") },
        text = { Text("Are you sure you want to delete this task? This action cannot be undone.") },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = TaskFlowError,
                    contentColor = androidx.compose.ui.graphics.Color.White
                )
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


@Preview(name = "DeleteConfirmDialog — Light")
@Composable
private fun DeleteConfirmDialogLightPreview() {
    Material3DesignTheme {
        DeleteConfirmDialog(onConfirm = {}, onDismiss = {})
    }
}

@Preview(name = "DeleteConfirmDialog — Dark")
@Composable
private fun DeleteConfirmDialogDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        DeleteConfirmDialog(onConfirm = {}, onDismiss = {})
    }
}
