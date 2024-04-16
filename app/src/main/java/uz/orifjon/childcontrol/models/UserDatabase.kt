package uz.orifjon.childcontrol.models

import androidx.room.RoomDatabase


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.TypeConverters
import uz.orifjon.childcontrol.models.child.Children
import uz.orifjon.childcontrol.models.child.Task
import uz.orifjon.childcontrol.models.child.converters.PairConverter
import uz.orifjon.childcontrol.models.daos.ChildrenDao
import uz.orifjon.childcontrol.models.daos.TaskDao
import uz.orifjon.childcontrol.models.daos.UserDao

@Database(entities = [User::class, Task::class, Children::class], version = 1, exportSchema = false)
@TypeConverters(PairConverter::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao
    abstract fun childrenDao(): ChildrenDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }

    }

}