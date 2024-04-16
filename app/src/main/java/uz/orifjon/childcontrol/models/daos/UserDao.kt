package uz.orifjon.childcontrol.models.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.orifjon.childcontrol.models.User


@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Query("SELECT * FROM user")
    fun getUser(): User?

}