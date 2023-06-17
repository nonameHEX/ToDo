package cz.tka.app.todo.ui.screens.task_list

import cz.tka.app.todo.model.Task

sealed class TaskListUIState {
    object Default : TaskListUIState()
    class Success(val tasks: List<Task>): TaskListUIState()
}