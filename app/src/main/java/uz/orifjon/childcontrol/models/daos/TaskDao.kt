package uz.orifjon.childcontrol.models.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.orifjon.childcontrol.models.local.Task
@Dao
interface TaskDao {

    @Insert
    fun insertTask(task: Task):Long


    @Query("SELECT * FROM task WHERE childrenId = :childrenId")
    fun getTasks(childrenId:Long):List<Task>

}