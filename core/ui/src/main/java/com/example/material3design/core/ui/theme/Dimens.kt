package com.example.material3design.core.ui.theme

import androidx.compose.ui.unit.dp

/**
 * Tokens de espaçamento e tamanho alinhados ao Material 3.
 * Use estes valores em vez de literais `dp` espalhados pelo código.
 */
object Dimens {

    // ── Espaçamento ──────────────────────────────────────────────────────────
    val spaceXxSmall = 2.dp
    val spaceXSmall = 4.dp
    val spaceSmall = 8.dp
    val spaceMedium = 16.dp
    val spaceLarge = 24.dp
    val spaceXLarge = 32.dp
    val spaceXxLarge = 48.dp

    // ── Tamanho de ícone ─────────────────────────────────────────────────────
    val iconSmall = 16.dp
    val iconMedium = 24.dp
    val iconLarge = 32.dp
    val iconXLarge = 48.dp

    // ── Elevação ─────────────────────────────────────────────────────────────
    val elevationNone = 0.dp
    val elevationLow = 1.dp
    val elevationMedium = 3.dp
    val elevationHigh = 6.dp

    // ── Alturas de componentes ────────────────────────────────────────────────
    val buttonHeight = 40.dp
    val inputHeight = 56.dp
    val navigationBarHeight = 80.dp
    val topAppBarHeight = 64.dp
    val chipHeight = 32.dp
    val listItemHeight = 56.dp   // one-line
    val listItemTwoLines = 72.dp
    val listItemThreeLines = 88.dp

    // ── Larguras ─────────────────────────────────────────────────────────────
    val navigationRailWidth = 80.dp
    val navigationDrawerWidth = 360.dp
    val sideSheetWidth = 320.dp

    // ── Raio de borda (referência ao M3 shape scale) ─────────────────────────
    val cornerNone = 0.dp
    val cornerSmall = 4.dp
    val cornerMedium = 12.dp
    val cornerLarge = 16.dp
    val cornerXLarge = 28.dp
    val cornerFull = 50.dp   // para shapes circulares
}
