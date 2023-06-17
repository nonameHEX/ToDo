package cz.tka.app.todo.ui.screens.task_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cz.tka.app.todo.model.Task
import cz.tka.app.todo.navigation.INavigationRouter
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    navigation: INavigationRouter,
    viewModel: TaskListViewModel = getViewModel()
){
    val tasks = remember { mutableListOf<Task>() }

    viewModel.taskListUIState.value.let {
        when(it){
            TaskListUIState.Default -> {
                viewModel.loadTasks()
            }
            is TaskListUIState.Success -> {
                tasks.clear()
                tasks.addAll(it.tasks)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { IconButton(enabled = false, onClick = {}){} },
                title = {
                    Text(text = "Task List")
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigation.navigateToAddEditTask(-1L) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ){
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ){
            TaskListScreenContent(
                navigation = navigation,
                actions = viewModel,
                tasks = tasks
            )
        }
    }
}

@Composable
fun TaskListScreenContent(
    navigation: INavigationRouter,
    actions: TaskListActions,
    tasks: List<Task>
){
    Text(text = "test")
}