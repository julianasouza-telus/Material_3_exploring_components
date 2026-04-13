package com.example.material3design.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Material3DesignTheme
import kotlinx.coroutines.launch

data class DrawerItem(val label: String, val icon: ImageVector, val badge: String? = null)

private val drawerItems = listOf(
    DrawerItem("Início", Icons.Default.Home),
    DrawerItem("Mensagens", Icons.Default.Email, "12"),
    DrawerItem("Notificações", Icons.Default.Notifications, "3"),
    DrawerItem("Favoritos", Icons.Default.Favorite),
    DrawerItem("Configurações", Icons.Default.Settings),
)

@Composable
fun NavigationDrawerScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableIntStateOf(0) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerSheetContent(
                selectedIndex = selectedItem,
                onItemSelected = { index ->
                    selectedItem = index
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Navigation Drawer", style = MaterialTheme.typography.headlineMedium)

            Text(
                "O Navigation Drawer desliza a partir da borda e exibe destinos de nível raiz.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(8.dp))

            Button(onClick = { scope.launch { drawerState.open() } }) {
                Icon(Icons.Default.Menu, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Abrir Modal Drawer")
            }

            Text(
                "Item selecionado: ${drawerItems[selectedItem].label}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

/* ---------- DRAWER CONTENT ---------- */

@Composable
private fun DrawerSheetContent(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    ModalDrawerSheet {
        Spacer(Modifier.height(12.dp))
        Text(
            "Material 3 App",
            modifier = Modifier.padding(horizontal = 28.dp, vertical = 16.dp),
            style = MaterialTheme.typography.titleLarge
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(8.dp))

        drawerItems.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = { Text(item.label) },
                selected = selectedIndex == index,
                icon = { Icon(item.icon, contentDescription = item.label) },
                badge = item.badge?.let { { Text(it) } },
                onClick = { onItemSelected(index) },
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }

        Spacer(Modifier.height(16.dp))
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(8.dp))

        NavigationDrawerItem(
            label = { Text("Sair") },
            selected = false,
            icon = { Icon(Icons.Default.Close, contentDescription = "Sair") },
            onClick = {},
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}

/* ---------- PREVIEWS ---------- */

@Preview(name = "Light — Drawer Aberto", showBackground = true)
@Composable
private fun NavigationDrawerLightPreview() {
    Material3DesignTheme {
        DrawerSheetContent(selectedIndex = 0, onItemSelected = {})
    }
}

@Preview(name = "Dark — Drawer Aberto", showBackground = true)
@Composable
private fun NavigationDrawerDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        DrawerSheetContent(selectedIndex = 2, onItemSelected = {})
    }
}
