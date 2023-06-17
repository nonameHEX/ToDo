package cz.tka.app.todo.navigation

sealed class Destination(val route: String){
    object TaskListScreen: Destination(route = "task_list")
    object AddEditTaskScreen: Destination(route = "add_edit_task")
}