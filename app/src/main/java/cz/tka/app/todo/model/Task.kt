package cz.tka.app.todo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(

    @ColumnInfo(name = "text")
    var text: String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "to_date")
    var toDate: Long? = null

    @ColumnInfo(name = "task_state")
    var taskState: Boolean = false
}