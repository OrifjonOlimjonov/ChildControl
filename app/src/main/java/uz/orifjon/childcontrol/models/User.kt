package uz.orifjon.childcontrol.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(
    @PrimaryKey
    val id:Int = 0,
    val name:String,
    val phoneNumber:String,
    val password:String
):Serializable