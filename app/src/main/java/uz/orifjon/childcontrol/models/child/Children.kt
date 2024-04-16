package uz.orifjon.childcontrol.models.child

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
class Children {
    @PrimaryKey(autoGenerate = true)
    val childId: Long = 0
    val locationNow: Pair<Double, Double>? = null
    @Relation(parentColumn = "childId",
        entityColumn = "taskId")
    val taskList: List<Task>? = null

}