package com.example.material3design.finalapp.data

data class Task(
    val id: String,
    val title: String,
    val description: String = "",
    val important: Boolean = false,
    val completed: Boolean = false
)

val sampleTasks = listOf(
    Task(
        id = "1",
        title = "Review Material Design 3 guidelines",
        description = "Study the latest MD3 specifications and component patterns",
        important = true,
        completed = false
    ),
    Task(
        id = "2",
        title = "Build TaskFlow prototype",
        description = "Create a clean UI showcasing MD3 components",
        important = true,
        completed = false
    ),
    Task(
        id = "3",
        title = "Test dark theme support",
        description = "Ensure all components work well in dark mode",
        important = false,
        completed = true
    )
)
