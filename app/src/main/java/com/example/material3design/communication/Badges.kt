package com.example.material3design.communication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.ui.theme.Material3DesignTheme

@Composable
fun BadgesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Badges", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("Icon Badges")
        IconBadgesSection()

        SectionTitle("Button Badges")
        ButtonBadgesSection()

        SectionTitle("Navigation Badges")
        NavigationBadgesSection()

        SectionTitle("States & Variants")
        VariantsSection()
    }
}

/* ---------- ICON BADGES ---------- */

@Composable
private fun IconBadgesSection() {
    Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
        BadgedBox(badge = { Badge { Text("1") } }) {
            Icon(Icons.Default.Notifications, contentDescription = null)
        }

        BadgedBox(badge = { Badge { Text("99+") } }) {
            Icon(Icons.Default.Email, contentDescription = null)
        }

        BadgedBox(badge = { Badge() }) {
            Icon(Icons.Default.Favorite, contentDescription = null)
        }

        BadgedBox(badge = {
            Badge(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            ) {
                Text("!")
            }
        }) {
            Icon(Icons.Default.ShoppingCart, contentDescription = null)
        }
    }
}

/* ---------- BUTTON BADGES ---------- */

@Composable
private fun ButtonBadgesSection() {
    Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
        BadgedBox(badge = { Badge { Text("3") } }) {
            Button(onClick = {}) {
                Text("Messages")
            }
        }

        BadgedBox(badge = { Badge() }) {
            OutlinedButton(onClick = {}) {
                Text("Updates")
            }
        }

        BadgedBox(badge = { Badge { Text("7") } }) {
            FilledTonalButton(onClick = {}) {
                Text("Cart")
            }
        }
    }
}

/* ---------- NAVIGATION BADGES ---------- */

@Composable
private fun NavigationBadgesSection() {
    Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
        BadgedBox(badge = { Badge { Text("5") } }) {
            Icon(Icons.Default.Notifications, contentDescription = null)
        }

        BadgedBox(badge = { Badge() }) {
            Icon(Icons.Default.Email, contentDescription = null)
        }
    }
}

/* ---------- VARIANTS & STATES ---------- */

@Composable
private fun VariantsSection() {
    Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
        // Dot only
        BadgedBox(badge = { Badge() }) {
            Icon(Icons.Default.Notifications, contentDescription = null)
        }

        // Small number
        BadgedBox(badge = { Badge { Text("2") } }) {
            Icon(Icons.Default.Favorite, contentDescription = null)
        }

        // Large number overflow
        BadgedBox(badge = { Badge { Text("99+") } }) {
            Icon(Icons.Default.Email, contentDescription = null)
        }

        // Custom color
        BadgedBox(badge = {
            Badge(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            ) {
                Text("NEW")
            }
        }) {
            Icon(Icons.Default.ShoppingCart, contentDescription = null)
        }
    }
}

/* ---------- UTILS ---------- */

@Composable
private fun SectionTitle(title: String) {
    Text(text = title, style = MaterialTheme.typography.titleMedium)
}

/* ---------- PREVIEWS ---------- */

@Preview(name = "Light", showBackground = true)
@Composable
private fun BadgesScreenPreview() {
    Material3DesignTheme {
        BadgesScreen()
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun BadgesScreenDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        BadgesScreen()
    }
}
