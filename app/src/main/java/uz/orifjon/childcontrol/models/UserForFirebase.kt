package uz.orifjon.childcontrol.models

import java.io.Serializable


class UserForFirebase : Serializable {
    var uid: String = ""
    var name: String = ""
    var phoneNumber: String = ""
    var password: String = ""
    var userToken: String = ""
    var childList: List<ChildrenForFirebase>? = null

    constructor()

    constructor(uid: String) {
        this.uid = uid
    }

    constructor(uid: String, name: String) {
        this.uid = uid
        this.name = name
    }

    constructor(uid: String, name: String, phoneNumber: String) {
        this.uid = uid
        this.name = name
        this.phoneNumber = phoneNumber
    }

    constructor(uid: String, name: String, phoneNumber: String, password: String) {
        this.uid = uid
        this.name = name
        this.phoneNumber = phoneNumber
        this.password = password
    }

    constructor(
        uid: String,
        name: String,
        phoneNumber: String,
        password: String,
        userToken: String
    ) {
        this.uid = uid
        this.name = name
        this.phoneNumber = phoneNumber
        this.password = password
        this.userToken = userToken
    }
    constructor(
        uid: String,
        name: String,
        phoneNumber: String,
        password: String,
        userToken: String,
        taskList:List<TaskForFirebase>
    ) {
        this.uid = uid
        this.name = name
        this.phoneNumber = phoneNumber
        this.password = password
        this.userToken = userToken
    }



}