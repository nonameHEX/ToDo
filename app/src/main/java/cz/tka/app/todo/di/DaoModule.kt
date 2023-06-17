package cz.tka.app.todo.di

import cz.tka.app.todo.database.TasksAppDatabase
import cz.tka.app.todo.database.task.TasksDao
import org.koin.dsl.module

val daoModule = module {
    fun provideTasksDao(database: TasksAppDatabase): TasksDao = database.taskDao()

    single { provideTasksDao(get()) }
}