package uz.orifjon.childcontrol.models

import uz.orifjon.childcontrol.models.local.Task
import java.io.Serializable


class ChildrenForFirebase : Serializable {
    var childId: String = ""
    var name: String = ""
    var age: String = ""
    var isLocate: Boolean = false
    var usedApps: List<String>? = null
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
        this.age = age
    }


    constructor(
        childId: String,
        name: String,
        age: String,
        localNow: ChildLocation
    ) {
        this.childId = childId
        this.name = name
        this.age = age
        this.locationNow = localNow
    }

    constructor(
        childId: String,
        name: String,
        age: String,
        localNow: ChildLocation,
        taskList: List<Task>
    ) {
        this.childId = childId
        this.name = name
        this.age = age
        this.locationNow = localNow
        this.taskList = taskList
    }


    constructor(
        childId: String,
        name: String,
        age: String,
        usedApps: List<String>,
        localNow: ChildLocation,
        taskList: List<Task>
    ){
        this.childId = childId
        this.name = name
        this.age = age
        this.usedApps = usedApps
        this.locationNow = localNow
        this.taskList = taskList
    }

    override fun equals(other: Any?): Boolean {
        return other is ChildrenForFirebase &&
                this.childId == other.childId &&
                this.name == other.name &&
                this.age == other.age &&
                this.taskList == other.taskList
    }

    override fun hashCode(): Int {
        var result = childId.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + age.hashCode()
        result = 31 * result + isLocate.hashCode()
        result = 31 * result + (locationNow?.hashCode() ?: 0)
        result = 31 * result + (taskList?.hashCode() ?: 0)
        return result
    }

}