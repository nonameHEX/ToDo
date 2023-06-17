package cz.tka.app.todo.ui.screens.task_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.tka.app.todo.architecture.BaseViewModel
import cz.tka.app.todo.database.task.ITasksRepository
import kotlinx.coroutines.launch

class TaskListViewModel(private val repository: ITasksRepository): BaseViewModel(), TaskListActions{
    val taskListUIState: MutableState<TaskListUIState> = mutableStateOf(TaskListUIState.Default)

    fun loadTasks(){
        launch {
            repository.getAll().collect {
                taskListUIState.value = TaskListUIState.Success(it)
            }
        }
    }
}