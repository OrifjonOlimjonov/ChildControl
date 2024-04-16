package uz.orifjon.childcontrol.models.local

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Children {
    @PrimaryKey(autoGenerate = true)
    var childId: Long = 0
    var name: String = ""
    var password: String = ""
    var locationNow: Pair<Double, Double>? = null
    var userId:String = ""
    constructor(childId: Long) {
        this.childId = childId
    }

    @Ignore
    constructor(name: String, password: String, location: Pair<Double, Double>) {
        this.name = name
        this.password = password
        this.locationNow = location
    }

    @Ignore
    constructor(childId: Long, locationNow: Pair<Double, Double>) {
        this.childId = childId
        this.locationNow = locationNow
    }


}