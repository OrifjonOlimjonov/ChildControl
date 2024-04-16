package uz.orifjon.childcontrol.models.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.orifjon.childcontrol.models.local.Children

@Dao
interface ChildrenDao {

    @Insert
    fun insertChildren(children: Children): Long

    @Query("SELECT * FROM children where userId = :userId")
    fun getChildren(userId: String): List<Children>

}