package com.example.material3design.taskapp.data

data class TaskData (
    val id: String,
    val title: String,
    val description: String = "",
    val important: Boolean = false,
    val completed: Boolean = false
)

val sampleTasks = listOf(
    TaskData(id = "1", title = "Estudar Material Design 3", important = true),
    TaskData(id = "2", title = "Construir uma tela de menu", description = "Para o app da mentoria"),
    TaskData(id = "3", title = "Testar dark mode", completed = true)
)