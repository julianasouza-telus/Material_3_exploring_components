package com.example.material3design.containment

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
import com.example.material3design.ui.theme.Dimens
import com.example.material3design.ui.theme.Material3DesignTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetsScreen() {
    val scaffoldState = rememberBottomSheetScaffoldState()
    var showModal by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 96.dp,
        sheetContent = { PersistentSheetContent() }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(Dimens.spaceMedium),
            verticalArrangement = Arrangement.spacedBy(Dimens.spaceLarge)
        ) {
            Text("Bottom Sheets", style = MaterialTheme.typography.headlineMedium)

            // BottomSheetScaffold
            SectionTitle("BottomSheetScaffold")
            Text(
                "Sheet persistente, sempre visível com peek height. " +
                        "Pode ser expandida ou contraída programaticamente.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(horizontalArrangement = Arrangement.spacedBy(Dimens.spaceSmall)) {
                Button(onClick = { scope.launch { scaffoldState.bottomSheetState.expand() } }) {
                    Icon(Icons.Default.KeyboardArrowUp, contentDescription = null)
                    Spacer(Modifier.width(Dimens.spaceXSmall))
                    Text("Expandir")
                }
                OutlinedButton(onClick = { scope.launch { scaffoldState.bottomSheetState.partialExpand() } }) {
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                    Spacer(Modifier.width(Dimens.spaceXSmall))
                    Text("Minimizar")
                }
            }

            HorizontalDivider()

            // ModalBottomSheet
            SectionTitle("ModalBottomSheet")
            Text(
                "Abre sobre o conteúdo com scrim, bloqueando interação com o fundo. " +
                        "Fechável ao tocar fora ou deslizar para baixo.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Button(onClick = { showModal = true }) {
                Icon(Icons.Default.OpenInNew, contentDescription = null)
                Spacer(Modifier.width(Dimens.spaceSmall))
                Text("Abrir Modal Bottom Sheet")
            }
        }
    }

    if (showModal) {
        val modalState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
        ModalBottomSheet(
            onDismissRequest = { showModal = false },
            sheetState = modalState
        ) {
            ModalSheetContent(onDismiss = { showModal = false })
        }
    }
}

/* ---------- SHEET CONTENTS ---------- */

@Composable
private fun PersistentSheetContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.spaceMedium, vertical = Dimens.spaceSmall),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceSmall)
    ) {
        Text(
            "BottomSheetScaffold — Deslize para expandir",
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            "Este sheet persiste na tela. Você pode ver o conteúdo parcialmente no peek.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimens.spaceSmall)
        ) {
            listOf("Ação 1", "Ação 2", "Ação 3").forEach { label ->
                AssistChip(
                    onClick = {},
                    label = { Text(label) }
                )
            }
        }
        Spacer(Modifier.height(Dimens.spaceSmall))
    }
}

@Composable
private fun ModalSheetContent(onDismiss: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.spaceMedium)
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Modal Bottom Sheet", style = MaterialTheme.typography.titleLarge)
        Text(
            "Este sheet aparece sobre o conteúdo. " +
                    "Toque fora ou arraste para baixo para fechar.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        HorizontalDivider()

        listOf(
            Icons.Default.Share to "Compartilhar",
            Icons.Default.Edit to "Editar",
            Icons.Default.Favorite to "Favoritar",
            Icons.Default.Delete to "Excluir"
        ).forEach { (icon, label) ->
            ListItem(
                headlineContent = { Text(label) },
                leadingContent = { Icon(icon, contentDescription = null) }
            )
        }

        Spacer(Modifier.height(Dimens.spaceSmall))
        TextButton(
            onClick = onDismiss,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Fechar")
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
private fun BottomSheetsLightPreview() {
    Material3DesignTheme { BottomSheetsScreen() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Dark", showBackground = true)
@Composable
private fun BottomSheetsDarkPreview() {
    Material3DesignTheme(darkTheme = true) { BottomSheetsScreen() }
}
