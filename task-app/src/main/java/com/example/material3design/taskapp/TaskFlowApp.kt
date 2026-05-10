package com.example.material3design.taskapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.material3design.core.ui.theme.Material3DesignTheme
import com.example.material3design.taskapp.data.TaskData
import com.example.material3design.taskapp.data.sampleTasks
import com.example.material3design.taskapp.ui.AddTaskDialog
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFlowApp() {

    var addDialogOpen by remember { mutableStateOf(false) }
    val tasks = remember { mutableStateListOf(*sampleTasks.toTypedArray()) }
    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarIsSuccess by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    //aula 5

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TaskFlow", fontWeight = FontWeight.Medium) },
                actions = { IconButton(onClick = { /* filtro - aula 8 */ }) {
                    //TODO: trocar o icone
                    Icon(Icons.Default.FilterList, contentDescription = "Filtrar") }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { addDialogOpen = true},
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Nova tarefa")
            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                val containerColor = if (snackbarIsSuccess) MaterialTheme.colorScheme.tertiary
                else MaterialTheme.colorScheme.primary
                val contentColor = if (snackbarIsSuccess) MaterialTheme.colorScheme.onTertiary
                else MaterialTheme.colorScheme.onPrimary

                Snackbar(
                    snackbarData = data,
                    containerColor = containerColor,
                    contentColor = contentColor,
                    actionColor = contentColor
                )
            }
        }
    ) { paddingValues ->
        // conteudo nas proximas aulas
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize())
    }

    if (addDialogOpen){
        AddTaskDialog(
            onDismiss = { addDialogOpen = false},
            onSave = { title, description, important ->
                tasks.add(
                    TaskData(
                        id = System.currentTimeMillis().toString(),
                        title = title,
                        description = description,
                        important = important
                    )
                )
                addDialogOpen = false
                snackbarIsSuccess = true
                scope.launch {
                    snackbarHostState.showSnackbar("Task created")
                }
            }
        )
    }

}

@Preview
@Composable

private fun TaskFlowAppPreview(){
    Material3DesignTheme() {
        TaskFlowApp()
    }
}