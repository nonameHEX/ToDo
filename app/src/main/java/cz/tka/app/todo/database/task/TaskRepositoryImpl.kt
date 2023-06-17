package cz.tka.app.todo.database.task

import cz.tka.app.todo.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(private val tasksDao: TasksDao): ITasksRepository {
    override fun getAll(): Flow<List<Task>> {
        return tasksDao.getAll()
    }

    override suspend fun findById(id: Long): Task {
        return tasksDao.findById(id)
    }

    override suspend fun insert(task: Task): Long {
        return tasksDao.insert(task)
    }

    override suspend fun update(task: Task) {
        tasksDao.update(task)
    }

    override suspend fun delete(task: Task) {
        tasksDao.delete(task)
    }

    override suspend fun changeTaskState(id: Long, taskState: Boolean) {
        tasksDao.changeTaskState(id = id, taskState = taskState)
    }

}