package com.example.material3design.dataInput

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Material3DesignTheme
import kotlin.math.roundToInt

@Composable
fun SlidersScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Sliders", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("Contínuo")
        ContinuousSection()

        SectionTitle("Com Passos")
        SteppedSection()

        SectionTitle("Intervalo (Range)")
        RangeSection()

        SectionTitle("Desabilitado")
        DisabledSection()
    }
}

/* ---------- CONTINUOUS ---------- */

@Composable
private fun ContinuousSection() {
    var volume by remember { mutableStateOf(0.4f) }
    var brightness by remember { mutableStateOf(0.75f) }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Volume", style = MaterialTheme.typography.bodyMedium)
            Text("${(volume * 100).roundToInt()}%", style = MaterialTheme.typography.bodyMedium)
        }
        Slider(value = volume, onValueChange = { volume = it })

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Brilho", style = MaterialTheme.typography.bodyMedium)
            Text("${(brightness * 100).roundToInt()}%", style = MaterialTheme.typography.bodyMedium)
        }
        Slider(value = brightness, onValueChange = { brightness = it })
    }
}

/* ---------- STEPPED ---------- */

@Composable
private fun SteppedSection() {
    var level by remember { mutableStateOf(2f) }
    var rating by remember { mutableStateOf(3f) }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Nível (0–5)", style = MaterialTheme.typography.bodyMedium)
            Text(level.roundToInt().toString(), style = MaterialTheme.typography.bodyMedium)
        }
        Slider(
            value = level,
            onValueChange = { level = it },
            valueRange = 0f..5f,
            steps = 4
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Avaliação (1–5)", style = MaterialTheme.typography.bodyMedium)
            Text("${rating.roundToInt()} ★", style = MaterialTheme.typography.bodyMedium)
        }
        Slider(
            value = rating,
            onValueChange = { rating = it },
            valueRange = 1f..5f,
            steps = 3
        )
    }
}

/* ---------- RANGE ---------- */

@Composable
private fun RangeSection() {
    var priceRange by remember { mutableStateOf(20f..80f) }
    var ageRange by remember { mutableStateOf(18f..65f) }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Preço", style = MaterialTheme.typography.bodyMedium)
            Text(
                "R$ ${priceRange.start.roundToInt()} – R$ ${priceRange.endInclusive.roundToInt()}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        RangeSlider(
            value = priceRange,
            onValueChange = { priceRange = it },
            valueRange = 0f..200f
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Faixa etária", style = MaterialTheme.typography.bodyMedium)
            Text(
                "${ageRange.start.roundToInt()} – ${ageRange.endInclusive.roundToInt()} anos",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        RangeSlider(
            value = ageRange,
            onValueChange = { ageRange = it },
            valueRange = 0f..100f
        )
    }
}

/* ---------- DISABLED ---------- */

@Composable
private fun DisabledSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Contínuo desabilitado", style = MaterialTheme.typography.bodySmall)
        Slider(value = 0.6f, onValueChange = {}, enabled = false)

        Text("Com passos desabilitado", style = MaterialTheme.typography.bodySmall)
        Slider(
            value = 3f,
            onValueChange = {},
            valueRange = 0f..5f,
            steps = 4,
            enabled = false
        )

        Text("Range desabilitado", style = MaterialTheme.typography.bodySmall)
        RangeSlider(
            value = 30f..70f,
            onValueChange = {},
            valueRange = 0f..100f,
            enabled = false
        )
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
private fun SlidersLightPreview() {
    Material3DesignTheme {
        SlidersScreen()
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun SlidersDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        SlidersScreen()
    }
}
