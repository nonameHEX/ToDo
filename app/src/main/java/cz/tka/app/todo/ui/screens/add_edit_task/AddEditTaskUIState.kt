package cz.tka.app.todo.ui.screens.add_edit_task

sealed class AddEditTaskUIState {
    object Loading: AddEditTaskUIState()
    object Default: AddEditTaskUIState()
    object TaskLoaded: AddEditTaskUIState()
    object TaskSaved: AddEditTaskUIState()
    object TaskDeleted: AddEditTaskUIState()
    object TaskStateChanged : AddEditTaskUIState()
}