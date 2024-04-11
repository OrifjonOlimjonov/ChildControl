package uz.orifjon.childcontrol.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUser(id: Int): User?

}