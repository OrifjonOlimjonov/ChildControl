package uz.orifjon.childcontrol.models

import uz.orifjon.childcontrol.models.local.Task
import java.io.Serializable


class ChildrenForFirebase : Serializable {
    var childId: String = ""
    var name: String = ""
    var userName: String = ""
    var password: String = ""
    var isLocate:Boolean = false
    var locationNow: ChildLocation? = null
    var taskList: List<Task>? = null

    constructor()
    constructor(childId: String) {
        this.childId = childId
    }

    constructor(childId: String, name: String) {
        this.childId = childId
        this.name = name
    }

    constructor(childId: String, name: String, userName: String) {
        this.childId = childId
        this.name = name
        this.userName = userName
    }

    constructor(childId: String, name: String, userName: String, password: String) {
        this.childId = childId
        this.name = name
        this.userName = userName
        this.password = password
    }


    constructor(
        childId: String,
        name: String,
        userName: String,
        password: String,
        localNow: ChildLocation
    ) {
        this.childId = childId
        this.name = name
        this.userName = userName
        this.password = password
        this.locationNow = localNow
    }

    constructor(
        childId: String,
        name: String,
        userName: String,
        password: String,
        localNow: ChildLocation,
        taskList: List<Task>
    ) {
        this.childId = childId
        this.name = name
        this.userName = userName
        this.password = password
        this.locationNow = localNow
        this.taskList = taskList
    }

    override fun equals(other: Any?): Boolean {
        return other is ChildrenForFirebase &&
                this.childId == other.childId &&
                this.name == other.name &&
                this.userName == other.userName &&
                this.password == other.password &&
                this.taskList == other.taskList
    }

    override fun hashCode(): Int {
        var result = childId.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + userName.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + isLocate.hashCode()
        result = 31 * result + (locationNow?.hashCode() ?: 0)
        result = 31 * result + (taskList?.hashCode() ?: 0)
        return result
    }

}