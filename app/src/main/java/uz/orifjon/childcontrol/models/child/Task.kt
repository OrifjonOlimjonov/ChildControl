package uz.orifjon.childcontrol.models.child

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
class Task {
    @PrimaryKey(autoGenerate = true)
    var taskId: Long = 0
    var title: String = ""
    var description: String = ""
    var deadline: String? = null


    constructor()

    constructor(taskId: Long) {
        this.taskId = taskId
    }

    constructor(title: String) {
        this.title = title
    }

    constructor(title: String, description: String) {
        this.title = title
        this.description = description
    }

    constructor(title: String, description: String, deadline: String) {
        this.title = title
        this.description = description
        this.deadline = deadline
    }
}