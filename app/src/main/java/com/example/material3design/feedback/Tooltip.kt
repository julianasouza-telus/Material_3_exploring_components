package com.example.material3design.feedback

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
fun TooltipScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Tooltip", style = MaterialTheme.typography.headlineMedium)

        Text(
            "Tooltips fornecem contexto adicional ao passar o cursor ou manter pressionado " +
                    "um elemento interativo.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        SectionTitle("PlainTooltip — em IconButton")
        Text(
            "Mantenha pressionado (long press) o ícone para ver o tooltip.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        PlainTooltipSection()

        SectionTitle("PlainTooltip — em Button")
        PlainTooltipButtonSection()

        SectionTitle("RichTooltip — com título e ação")
        Text(
            "Rich tooltips permitem conteúdo mais elaborado com título, corpo e ação.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        RichTooltipSection()

        SectionTitle("Persistente (isPersistent = true)")
        Text(
            "Permanece visível após ser aberto, até que outro elemento seja tocado.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        PersistentRichTooltipSection()
    }
}

/* ---------- SECTIONS ---------- */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlainTooltipSection() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            tooltip = { PlainTooltip { Text("Favoritar") } },
            state = rememberTooltipState()
        ) {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Favorite, contentDescription = "Favoritar")
            }
        }

        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            tooltip = { PlainTooltip { Text("Compartilhar") } },
            state = rememberTooltipState()
        ) {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Share, contentDescription = "Compartilhar")
            }
        }

        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            tooltip = { PlainTooltip { Text("Excluir item") } },
            state = rememberTooltipState()
        ) {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Delete, contentDescription = "Excluir")
            }
        }

        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            tooltip = { PlainTooltip { Text("Mais opções") } },
            state = rememberTooltipState()
        ) {
            IconButton(onClick = {}) {
                Icon(Icons.Default.MoreVert, contentDescription = "Mais")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlainTooltipButtonSection() {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            PlainTooltip {
                Text("Salva o documento atual")
            }
        },
        state = rememberTooltipState()
    ) {
        Button(onClick = {}) {
            Icon(Icons.Default.Check, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Salvar")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RichTooltipSection() {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
        tooltip = {
            RichTooltip(
                title = { Text("Informações") },
                action = {
                    TextButton(onClick = {}) { Text("Saiba mais") }
                }
            ) {
                Text(
                    "Este componente exibe tooltips ricos com título, " +
                            "corpo de texto e uma ação opcional."
                )
            }
        },
        state = rememberTooltipState()
    ) {
        IconButton(onClick = {}) {
            Icon(Icons.Default.Info, contentDescription = "Informações")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersistentRichTooltipSection() {
    val tooltipState = rememberTooltipState(isPersistent = true)

    TooltipBox(
        positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
        tooltip = {
            RichTooltip(
                title = { Text("Configurações avançadas") },
                action = {
                    TextButton(onClick = {}) { Text("Abrir configurações") }
                }
            ) {
                Text(
                    "Personalize o comportamento desta função nas configurações. " +
                            "Toque em outro lugar para fechar."
                )
            }
        },
        state = tooltipState
    ) {
        FilledTonalButton(onClick = {}) {
            Icon(Icons.Default.Settings, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Configurações")
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
private fun TooltipLightPreview() {
    Material3DesignTheme { TooltipScreen() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Dark", showBackground = true)
@Composable
private fun TooltipDarkPreview() {
    Material3DesignTheme(darkTheme = true) { TooltipScreen() }
}
