package uz.orifjon.childcontrol.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import uz.orifjon.childcontrol.models.child.Children
import java.io.Serializable

@Entity
class User : Serializable {
    @PrimaryKey
    var uid: String = ""
    var name: String = ""
    var phoneNumber: String = ""
    var password: String = ""
    var userToken: String = ""
    var children:List<Children>?=null

    constructor()
    @Ignore
    constructor(uid: String) {
        this.uid = uid
    }

    @Ignore
    constructor(uid: String, name: String) {
        this.uid = uid
        this.name = name
    }

    @Ignore
    constructor(uid: String, name: String, phoneNumber: String) {
        this.uid = uid
        this.name = name
        this.phoneNumber = phoneNumber
    }

    @Ignore
    constructor(uid: String, name: String, phoneNumber: String, password: String) {
        this.uid = uid
        this.name = name
        this.phoneNumber = phoneNumber
        this.password = password
    }

    @Ignore
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


}