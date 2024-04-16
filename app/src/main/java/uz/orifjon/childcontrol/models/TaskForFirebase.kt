package uz.orifjon.childcontrol.models

data class TaskForFirebase(
    var taskId: Long = 0,
    var title: String = "",
    var description: String = "",
    var deadline: String? = null
)