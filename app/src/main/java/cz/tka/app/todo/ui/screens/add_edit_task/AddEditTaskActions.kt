package cz.tka.app.todo.ui.screens.add_edit_task

interface AddEditTaskActions {
    fun saveTask()
    fun onTextChanged(text: String)
    fun onToDateChanged(date: Long?)
}