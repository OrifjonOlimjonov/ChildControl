package uz.orifjon.childcontrol.models.child

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Task {
    @PrimaryKey(autoGenerate = true)
    var taskId: Long = 0
    var title: String = ""
    var description: String = ""
    var deadline: String? = null
    var childrenId:Long = 0



    constructor(taskId: Long) {
        this.taskId = taskId
    }

    @Ignore
    constructor(title: String) {
        this.title = title
    }

    @Ignore
    constructor(title: String, description: String) {
        this.title = title
        this.description = description
    }

    @Ignore
    constructor(title: String, description: String, deadline: String) {
        this.title = title
        this.description = description
        this.deadline = deadline
    }
}