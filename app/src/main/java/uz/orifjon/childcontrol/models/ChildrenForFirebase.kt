package uz.orifjon.childcontrol.models

import uz.orifjon.childcontrol.models.local.Task
import java.io.Serializable


class ChildrenForFirebase : Serializable {
    var childId: String = ""
    var name: String = ""
    var userName: String = ""
    var password: String = " "
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

}