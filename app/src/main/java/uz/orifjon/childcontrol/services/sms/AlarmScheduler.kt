package uz.orifjon.childcontrol.services.sms

interface AlarmScheduler {
    fun schedule(item: SMSAlarmItem)
    fun cancel(item: SMSAlarmItem)
}