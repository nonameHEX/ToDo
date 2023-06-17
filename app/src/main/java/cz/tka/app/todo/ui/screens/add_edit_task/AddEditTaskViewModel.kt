package cz.tka.app.todo.ui.screens.add_edit_task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.tka.app.todo.architecture.BaseViewModel
import cz.tka.app.todo.database.task.ITasksRepository
import kotlinx.coroutines.launch

class AddEditTaskViewModel(private val repository: ITasksRepository): BaseViewModel(), AddEditTaskActions {
    val addEditTaskUIState: MutableState<AddEditTaskUIState> = mutableStateOf(AddEditTaskUIState.Loading)

    var taskId: Long? = null

    var data: AddEditTaskScreenData = AddEditTaskScreenData()

    fun initTask() {
        if(taskId != null){
            launch {
                data.task = repository.findById(taskId!!)
            }
        }else {
            addEditTaskUIState.value = AddEditTaskUIState.TaskLoaded
        }
    }

    override fun saveTask() {
        if(data.task.text.isEmpty()) {
            addEditTaskUIState.value = AddEditTaskUIState.TaskStateChanged
        }else {
            launch {
                if(taskId == null){
                    repository.insert(data.task)
                }else {
                    repository.update(data.task)
                }
            }
        }
    }

    fun deleteTask() {
        launch {
            repository.delete(data.task)
            addEditTaskUIState.value = AddEditTaskUIState.TaskDeleted
        }
    }

    override fun onTextChanged(text: String) {
        data.task.text = text
        addEditTaskUIState.value = AddEditTaskUIState.TaskStateChanged
    }

    override fun onToDateChanged(date: Long?) {
        data.task.toDate = date
        addEditTaskUIState.value = AddEditTaskUIState.TaskStateChanged
    }
}