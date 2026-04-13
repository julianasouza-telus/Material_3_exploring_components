package com.example.material3design.dataInput

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Dimens
import com.example.material3design.core.ui.theme.Material3DesignTheme

@Composable
fun SwitchScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Dimens.spaceMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceLarge)
    ) {
        Text("Switch", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("Básico")
        BasicSwitchSection()

        SectionTitle("Com Ícone no Thumb")
        ThumbIconSection()

        SectionTitle("Com Rótulo")
        LabeledSection()

        SectionTitle("Desabilitado")
        DisabledSection()
    }
}

/* ---------- SECTIONS ---------- */

@Composable
private fun BasicSwitchSection() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.spaceXLarge),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var off by remember { mutableStateOf(false) }
        var on by remember { mutableStateOf(true) }

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(Dimens.spaceXSmall)) {
            Switch(checked = off, onCheckedChange = { off = it })
            Text("Off", style = MaterialTheme.typography.labelSmall)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(Dimens.spaceXSmall)) {
            Switch(checked = on, onCheckedChange = { on = it })
            Text("On", style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
private fun ThumbIconSection() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.spaceXLarge),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var checked1 by remember { mutableStateOf(false) }
        var checked2 by remember { mutableStateOf(true) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimens.spaceXSmall)
        ) {
            Switch(
                checked = checked1,
                onCheckedChange = { checked1 = it },
                thumbContent = if (checked1) {
                    { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(SwitchDefaults.IconSize)) }
                } else null
            )
            Text("Check on", style = MaterialTheme.typography.labelSmall)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimens.spaceXSmall)
        ) {
            Switch(
                checked = checked2,
                onCheckedChange = { checked2 = it },
                thumbContent = {
                    Icon(
                        imageVector = if (checked2) Icons.Default.Check else Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
                }
            )
            Text("Sempre ícone", style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
private fun LabeledSection() {
    val labels = listOf(
        "Notificações push",
        "Sincronização automática",
        "Modo escuro",
        "Acessibilidade"
    )
    val states = remember { mutableStateListOf(true, false, false, true) }

    Column(verticalArrangement = Arrangement.spacedBy(Dimens.spaceXSmall)) {
        labels.forEachIndexed { index, label ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(label, style = MaterialTheme.typography.bodyMedium)
                Switch(
                    checked = states[index],
                    onCheckedChange = { states[index] = it }
                )
            }
        }
    }
}

@Composable
private fun DisabledSection() {
    Row(horizontalArrangement = Arrangement.spacedBy(Dimens.spaceXLarge)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(Dimens.spaceXSmall)) {
            Switch(checked = false, onCheckedChange = {}, enabled = false)
            Text("Off", style = MaterialTheme.typography.labelSmall)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(Dimens.spaceXSmall)) {
            Switch(checked = true, onCheckedChange = {}, enabled = false)
            Text("On", style = MaterialTheme.typography.labelSmall)
        }
    }
}

/* ---------- UTILS ---------- */

@Composable
private fun SectionTitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleMedium)
}

/* ---------- PREVIEWS ---------- */

@Preview(name = "Light", showBackground = true)
@Composable
private fun SwitchLightPreview() {
    Material3DesignTheme { SwitchScreen() }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun SwitchDarkPreview() {
    Material3DesignTheme(darkTheme = true) { SwitchScreen() }
}
