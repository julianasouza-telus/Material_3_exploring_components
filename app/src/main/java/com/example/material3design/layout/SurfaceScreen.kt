package com.example.material3design.layout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.ui.theme.Material3DesignTheme

@Composable
fun SurfaceScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Surface", style = MaterialTheme.typography.headlineMedium)

        Text(
            "Surface é o container fundamental no Material 3. Define cor, forma, " +
                    "elevação tonal e sombra para o conteúdo que envolve.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        SectionTitle("Elevação Tonal (tonalElevation)")
        Text(
            "Eleva a cor de superfície misturando a primary com a surface. " +
                    "Quanto maior a elevação, mais saturada fica a cor.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        TonalElevationSection()

        SectionTitle("Sombra (shadowElevation)")
        Text(
            "shadowElevation adiciona sombra real ao redor do componente.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        ShadowElevationSection()

        SectionTitle("Formas (shape)")
        ShapesSection()

        SectionTitle("Cores de Container")
        ContainerColorsSection()

        SectionTitle("Surface clicável")
        ClickableSurfaceSection()
    }
}

/* ---------- SECTIONS ---------- */

@Composable
private fun TonalElevationSection() {
    val elevations = listOf(0.dp, 1.dp, 3.dp, 6.dp, 8.dp, 12.dp)
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        elevations.forEach { dp ->
            Surface(
                tonalElevation = dp,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        "${dp.value.toInt()}dp",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Composable
private fun ShadowElevationSection() {
    val elevations = listOf(0.dp, 2.dp, 4.dp, 8.dp, 16.dp)
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        elevations.forEach { dp ->
            Surface(
                shadowElevation = dp,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.size(52.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        "${dp.value.toInt()}",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Composable
private fun ShapesSection() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        listOf(
            "Extra Small" to MaterialTheme.shapes.extraSmall,
            "Small" to MaterialTheme.shapes.small,
            "Medium" to MaterialTheme.shapes.medium,
            "Large" to MaterialTheme.shapes.large,
            "Extra Large" to MaterialTheme.shapes.extraLarge
        ).forEach { (label, shape) ->
            Surface(
                shape = shape,
                tonalElevation = 4.dp,
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        label.split(" ").last(),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Composable
private fun ContainerColorsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        listOf(
            Triple("surface", MaterialTheme.colorScheme.surface, MaterialTheme.colorScheme.onSurface),
            Triple("surfaceVariant", MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.onSurfaceVariant),
            Triple("surfaceContainer", MaterialTheme.colorScheme.surfaceContainer, MaterialTheme.colorScheme.onSurface),
            Triple("surfaceContainerHigh", MaterialTheme.colorScheme.surfaceContainerHigh, MaterialTheme.colorScheme.onSurface),
            Triple("inverseSurface", MaterialTheme.colorScheme.inverseSurface, MaterialTheme.colorScheme.inverseOnSurface)
        ).forEach { (name, bg, fg) ->
            Surface(
                color = bg,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Text(name, style = MaterialTheme.typography.labelMedium, color = fg)
                }
            }
        }
    }
}

@Composable
private fun ClickableSurfaceSection() {
    var counter by remember { mutableIntStateOf(0) }
    Surface(
        onClick = { counter++ },
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 3.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Toque aqui — cliques: $counter",
                style = MaterialTheme.typography.bodyMedium
            )
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
private fun SurfaceLightPreview() {
    Material3DesignTheme { SurfaceScreen() }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun SurfaceDarkPreview() {
    Material3DesignTheme(darkTheme = true) { SurfaceScreen() }
}
