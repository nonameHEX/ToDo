package cz.tka.app.todo.di

import cz.tka.app.todo.ToDoApp
import cz.tka.app.todo.database.TasksAppDatabase
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(): TasksAppDatabase = TasksAppDatabase.getDatabase(ToDoApp.appContext)

    single { provideDatabase() }
}