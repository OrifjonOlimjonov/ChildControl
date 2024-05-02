package uz.orifjon.childcontrol.services.sms

import java.time.LocalDateTime

data class SMSAlarmItem(
    val time: LocalDateTime,
    val message: String
)