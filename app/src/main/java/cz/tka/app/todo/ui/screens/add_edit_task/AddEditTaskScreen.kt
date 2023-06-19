package cz.tka.app.todo.ui.screens.add_edit_task

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cz.tka.app.todo.R
import cz.tka.app.todo.navigation.INavigationRouter
import cz.tka.app.todo.ui.elements.InfoElement
import cz.tka.app.todo.utils.DateUtils
import org.koin.androidx.compose.getViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreen(
    navigation: INavigationRouter,
    viewModel: AddEditTaskViewModel = getViewModel(),
    id: Long?
){
    viewModel.taskId = id
    var data: AddEditTaskScreenData by remember { mutableStateOf(viewModel.data) }

    viewModel.addEditTaskUIState.value.let {
        when(it){
            AddEditTaskUIState.Default -> {

            }
            AddEditTaskUIState.Loading -> {
                LaunchedEffect(it) {
                    viewModel.initTask()
                }
            }
            AddEditTaskUIState.TaskLoaded -> {
                data = viewModel.data
                data.loadingScreen = false
            }
            AddEditTaskUIState.TaskSaved -> {
                LaunchedEffect(it) {
                    navigation.returnBack()
                }
            }
            AddEditTaskUIState.TaskDeleted -> {
                LaunchedEffect(it) {
                    navigation.returnBack()
                }
            }
            is AddEditTaskUIState.TaskStateChanged -> {
                data = viewModel.data
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navigation.returnBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null)
                    }
                },
                title = {
                    if(viewModel.taskId == null){
                        Text(text = "ADD")
                    }else {
                        Text(text = "EDIT")
                    }
                },
                actions = {
                    if (viewModel.taskId != null){
                        IconButton(onClick = { viewModel.deleteTask() }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                        }
                    }
                }
            )
        },
    ){
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ){
            AddEditTaskScreenContent(
                actions = viewModel,
                data = data
            )
        }
    }
}

@Composable
fun AddEditTaskScreenContent(
    actions: AddEditTaskActions,
    data: AddEditTaskScreenData
){
    if(!data.loadingScreen){
        val calendar = Calendar.getInstance()
        data.task.toDate?.let {
            calendar.timeInMillis = it
        }

        val y = calendar.get(Calendar.YEAR)
        val m = calendar.get(Calendar.MONTH)
        val d = calendar.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog = DatePickerDialog(LocalContext.current,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                actions.onToDateChanged(DateUtils.getUnixTime(year, month, dayOfMonth))
            }, y, m, d
        )


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            item {
                OutlinedTextField(
                    value = data.task.text,
                    onValueChange = { actions.onTextChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            item {
                InfoElement(
                    value = if (data.task.toDate != null) DateUtils.getDateString(data.task.toDate!!) else null,
                    hint = "Date",
                    leadingIcon = R.drawable.ic_calendar_today,
                    onClick = {
                        datePickerDialog.show()
                    }, onClearClick = {
                        actions.onToDateChanged(null)
                    }
                )
            }
            item {
                Spacer(
                    modifier = Modifier.height(500.dp)
                )
            }
            item {
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        actions.saveTask()
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White, containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Save")
                }

            }
        }
    }
}
