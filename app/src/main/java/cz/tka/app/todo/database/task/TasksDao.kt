package cz.tka.app.todo.database.task

import androidx.room.*
import cz.tka.app.todo.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {
    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun findById(id : Long): Task

    @Insert
    suspend fun insert(task: Task): Long

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("UPDATE tasks SET task_state = :taskState WHERE id = :id")
    suspend fun changeTaskState(id: Long, taskState: Boolean)

}