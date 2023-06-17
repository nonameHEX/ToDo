package cz.tka.app.todo.di

import cz.tka.app.todo.ui.screens.add_edit_task.AddEditTaskViewModel
import cz.tka.app.todo.ui.screens.task_list.TaskListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TaskListViewModel(get()) }
    viewModel { AddEditTaskViewModel(get()) }
}