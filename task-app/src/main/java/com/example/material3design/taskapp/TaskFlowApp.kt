package com.example.material3design.taskapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.material3design.taskapp.ui.FavoritesScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFlowApp() {

    var addDialogOpen by remember { mutableStateOf(false) }
    val tasks = remember { mutableStateListOf(*sampleTasks.toTypedArray()) }
    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarIsSuccess by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    var currentTab by remember { mutableIntStateOf(0) }
    var taskToDelete by remember { mutableStateOf<String?>(null) }

    fun toggleComplete(id: String) {
        val index = tasks.indexOfFirst { it.id == id }
        if (index >= 0) tasks[index] = tasks[index].copy(completed = !tasks[index].completed)
    }
    fun toggleImportant(id: String) {
        val index = tasks.indexOfFirst { it.id == id }
        if (index >= 0) tasks[index] = tasks[index].copy(important = !tasks[index].important)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = if (currentTab == 0) "TaskFlow" else "Favorites", fontWeight = FontWeight.Medium) },
                actions = { IconButton(onClick = { /* filtro - aula 8 */ }) {
                    Icon(Icons.Default.FilterList, contentDescription = "Filtrar") }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentTab == 0,
                    onClick = { currentTab = 0 },
                    icon = { Icon(Icons.Default.CheckCircle, contentDescription = "All Tasks")},
                    label = { Text("All Tasks")}
                )
                NavigationBarItem(
                    selected = currentTab == 1,
                    onClick = { currentTab = 1 },
                    icon = { Icon(Icons.Default.Star, contentDescription = "Favorites")},
                    label = { Text("Favorites")}
                )
            }
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
        when(currentTab) {
            0 -> Box(modifier = Modifier.padding(paddingValues).fillMaxSize())
            1 -> FavoritesScreen(
                tasks =  tasks.filter { it.important },
                onToggleComplete = { toggleComplete(it)},
                onToggleImportant = { toggleImportant(it)},
                onDeleteClick = { taskToDelete = it},
                modifier = Modifier.padding(paddingValues)
            )
        }
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