package cz.tka.app.todo.ui.screens.task_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.tka.app.todo.model.Task
import cz.tka.app.todo.navigation.INavigationRouter
import cz.tka.app.todo.utils.DateUtils
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
    LazyColumn {
        tasks.forEach{
            item(key = it.id){
                TaskRow(
                    task = it,
                    onRowClick = { navigation.navigateToAddEditTask(it.id) },
                    actions = actions
                )
            }
        }
    }
}

@Composable
fun TaskRow(
    task: Task,
    onRowClick: () -> Unit,
    actions: TaskListActions
){
    val taskStatus: MutableState<Boolean> = remember { mutableStateOf(task.taskState) }
    Row(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable(onClick = onRowClick),
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = taskStatus.value,
            onCheckedChange = {
                taskStatus.value = it
                actions.changeTaskState(task.id!!, it)}
        )
        Column {
            Text(
                text = if(task.toDate != null) {
                           "${task.text} do ${DateUtils.getDateString(task.toDate!!)}"
                       }else{
                           "${task.text}"
                       },
                maxLines = 2)
        }
    }
}