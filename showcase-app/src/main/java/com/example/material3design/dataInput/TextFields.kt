package com.example.material3design.dataInput

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3design.core.ui.theme.Material3DesignTheme

@Composable
fun TextFieldsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Text Fields", style = MaterialTheme.typography.headlineMedium)

        SectionTitle("Filled (padrão)")
        FilledSection()

        SectionTitle("Outlined")
        OutlinedSection()

        SectionTitle("Estados Especiais")
        StatesSection()

        SectionTitle("Multilinha e Contador")
        MultilineSection()
    }
}

/* ---------- FILLED ---------- */

@Composable
private fun FilledSection() {
    var basic by remember { mutableStateOf("") }
    var withIcon by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        TextField(
            value = basic,
            onValueChange = { basic = it },
            label = { Text("Label") },
            placeholder = { Text("Placeholder") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = withIcon,
            onValueChange = { withIcon = it },
            label = { Text("Com ícone e limpar") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (withIcon.isNotEmpty()) {
                    IconButton(onClick = { withIcon = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Limpar")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Ocultar senha" else "Mostrar senha"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/* ---------- OUTLINED ---------- */

@Composable
private fun OutlinedSection() {
    var basic by remember { mutableStateOf("") }
    var withIcon by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            value = basic,
            onValueChange = { basic = it },
            label = { Text("Label") },
            placeholder = { Text("Placeholder") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = withIcon,
            onValueChange = { withIcon = it },
            label = { Text("Com ícone") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (withIcon.isNotEmpty()) {
                    IconButton(onClick = { withIcon = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Limpar")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/* ---------- STATES ---------- */

@Composable
private fun StatesSection() {
    var errorText by remember { mutableStateOf("valor inválido") }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            value = errorText,
            onValueChange = { errorText = it },
            label = { Text("Estado de erro") },
            isError = true,
            supportingText = { Text("Este campo contém um erro") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = "Valor desabilitado",
            onValueChange = {},
            label = { Text("Desabilitado") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = "Apenas leitura",
            onValueChange = {},
            label = { Text("Somente leitura") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/* ---------- MULTILINE ---------- */

@Composable
private fun MultilineSection() {
    var text by remember { mutableStateOf("") }
    val maxChars = 200

    OutlinedTextField(
        value = text,
        onValueChange = { if (it.length <= maxChars) text = it },
        label = { Text("Texto longo") },
        minLines = 3,
        maxLines = 6,
        supportingText = {
            Text(
                "${text.length} / $maxChars",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}

/* ---------- UTILS ---------- */

@Composable
private fun SectionTitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleMedium)
}

/* ---------- PREVIEWS ---------- */

@Preview(name = "Light", showBackground = true)
@Composable
private fun TextFieldsLightPreview() {
    Material3DesignTheme {
        TextFieldsScreen()
    }
}

@Preview(name = "Dark", showBackground = true)
@Composable
private fun TextFieldsDarkPreview() {
    Material3DesignTheme(darkTheme = true) {
        TextFieldsScreen()
    }
}
