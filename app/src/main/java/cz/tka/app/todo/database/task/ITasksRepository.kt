package cz.tka.app.todo.database.task

import cz.tka.app.todo.model.Task
import kotlinx.coroutines.flow.Flow

interface ITasksRepository {
    fun getAll(): Flow<List<Task>>
    suspend fun findById(id : Long): Task
    suspend fun insert(task: Task): Long
    suspend fun update(task: Task)
    suspend fun delete(task: Task)
    suspend fun changeTaskState(id: Long, taskState: Boolean)

}