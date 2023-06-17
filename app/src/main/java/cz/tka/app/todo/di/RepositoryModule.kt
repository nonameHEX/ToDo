package cz.tka.app.todo.di

import cz.tka.app.todo.database.task.ITasksRepository
import cz.tka.app.todo.database.task.TaskRepositoryImpl
import cz.tka.app.todo.database.task.TasksDao
import org.koin.dsl.module

val repositoryModule = module {
    fun provideTasksRepository(dao: TasksDao): ITasksRepository{
        return TaskRepositoryImpl(dao)
    }

    single { provideTasksRepository(get()) }
}