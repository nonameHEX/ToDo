package cz.tka.app.todo.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import cz.tka.app.todo.database.task.TasksDao
import cz.tka.app.todo.model.Task


@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class TasksAppDatabase: RoomDatabase(){

    abstract fun taskDao(): TasksDao

    companion object {
        private var INSTANCE: TasksAppDatabase? = null

        fun getDatabase(context: Context): TasksAppDatabase {
            if(INSTANCE == null){
                synchronized(TasksAppDatabase::class.java) {
                    if(INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            TasksAppDatabase::class.java, "tasks_database"
                        ).fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}