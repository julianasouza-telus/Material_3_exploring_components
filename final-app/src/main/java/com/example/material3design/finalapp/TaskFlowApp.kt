package com.example.material3design.finalapp

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.material3design.core.ui.theme.Material3DesignTheme
import com.example.material3design.core.ui.theme.TaskFlowBackground
import com.example.material3design.core.ui.theme.TaskFlowPrimary
import com.example.material3design.core.ui.theme.TaskFlowSuccess
import com.example.material3design.finalapp.data.Task
import com.example.material3design.finalapp.data.sampleTasks
import com.example.material3design.finalapp.ui.AddTaskDialog
import com.example.material3design.finalapp.ui.DeleteConfirmDialog
import com.example.material3design.finalapp.ui.FavoritesScreen
import com.example.material3design.finalapp.ui.FilterBottomSheet
import com.example.material3design.finalapp.ui.TaskListScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFlowApp() {
    val tasks = remember { mutableStateListOf(*sampleTasks.toTypedArray()) }
    var currentTab by remember { mutableIntStateOf(0) }
    var filter by remember { mutableStateOf("all") }
    var addDialogOpen by remember { mutableStateOf(false) }
    var filterSheetOpen by remember { mutableStateOf(false) }
    var taskToDelete by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarIsSuccess by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

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
                title = {
                    Text(
                        text = if (currentTab == 0) "TaskFlow" else "Favorites",
                        fontWeight = FontWeight.Medium
                    )
                },
                actions = {
                    IconButton(onClick = { filterSheetOpen = true }) {
                        Icon(Icons.Default.FilterList, contentDescription = "Filtrar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = TaskFlowPrimary,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentTab == 0,
                    onClick = { currentTab = 0 },
                    icon = { Icon(Icons.Default.CheckCircle, contentDescription = null) },
                    label = { Text("All Tasks") }
                )
                NavigationBarItem(
                    selected = currentTab == 1,
                    onClick = { currentTab = 1 },
                    icon = { Icon(Icons.Default.Star, contentDescription = null) },
                    label = { Text("Favorites") }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { addDialogOpen = true },
                containerColor = TaskFlowPrimary,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Nova tarefa")
            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = if (snackbarIsSuccess) TaskFlowSuccess else MaterialTheme.colorScheme.primary,
                    contentColor = Color.White,
                    actionColor = Color.White
                )
            }
        },
        containerColor = TaskFlowBackground
    ) { padding ->
        AnimatedContent(
            targetState = currentTab,
            transitionSpec = {
                if (targetState > initialState)
                    slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
                else
                    slideInHorizontally { -it } togetherWith slideOutHorizontally { it }
            },
            label = "tabTransition"
        ) { tab ->
            when (tab) {
                0 -> TaskListScreen(
                    tasks = tasks,
                    filter = filter,
                    onFilterChange = { filter = it },
                    onToggleComplete = { toggleComplete(it) },
                    onToggleImportant = { toggleImportant(it) },
                    onDeleteClick = { taskToDelete = it },
                    modifier = Modifier.padding(padding)
                )
                1 -> FavoritesScreen(
                    tasks = tasks.filter { it.important },
                    onToggleComplete = { toggleComplete(it) },
                    onToggleImportant = { toggleImportant(it) },
                    onDeleteClick = { taskToDelete = it },
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }

    if (addDialogOpen) {
        AddTaskDialog(
            onDismiss = { addDialogOpen = false },
            onSave = { title, description, important ->
                tasks.add(
                    Task(
                        id = System.currentTimeMillis().toString(),
                        title = title,
                        description = description,
                        important = important
                    )
                )
                addDialogOpen = false
                snackbarIsSuccess = true
                scope.launch { snackbarHostState.showSnackbar("Task created") }
            }
        )
    }

    if (filterSheetOpen) {
        FilterBottomSheet(
            currentFilter = filter,
            onFilterSelect = { filter = it; filterSheetOpen = false },
            onDismiss = { filterSheetOpen = false }
        )
    }

    taskToDelete?.let { id ->
        DeleteConfirmDialog(
            onConfirm = {
                tasks.removeAll { it.id == id }
                taskToDelete = null
                snackbarIsSuccess = false
                scope.launch { snackbarHostState.showSnackbar("Task deleted") }
            },
            onDismiss = { taskToDelete = null }
        )
    }
}

@Preview(name = "TaskFlowApp — Light", showSystemUi = true)
@Composable
private fun TaskFlowAppLightPreview() {
    Material3DesignTheme(dynamicColor = false) {
        TaskFlowApp()
    }
}

@Preview(name = "TaskFlowApp — Dark", showSystemUi = true)
@Composable
private fun TaskFlowAppDarkPreview() {
    Material3DesignTheme(darkTheme = true, dynamicColor = false) {
        TaskFlowApp()
    }
}