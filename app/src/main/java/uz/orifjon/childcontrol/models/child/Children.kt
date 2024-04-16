package uz.orifjon.childcontrol.models.child

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
class Children {
    @PrimaryKey(autoGenerate = true)
    var childId: Long = 0
    var locationNow: Pair<Double, Double>? = null

    @Relation(
        parentColumn = "childId",
        entityColumn = "taskId"
    )
    var taskList: List<Task>? = null

    constructor(childId: Long) {
        this.childId = childId
    }

    constructor(childId: Long, locationNow: Pair<Double, Double>) {
        this.childId = childId
        this.locationNow = locationNow
    }

    constructor(childId: Long, locationNow: Pair<Double, Double>, taskList: List<Task>) {
        this.childId = childId
        this.locationNow = locationNow
        this.taskList = taskList
    }

}