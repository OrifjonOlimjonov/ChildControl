package uz.orifjon.childcontrol.services.reciever

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.ActivityCompat

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        println("Alarm triggered: $message")
        if (ActivityCompat.checkSelfPermission(context!!, Manifest.permission.SEND_SMS) != -1) {
            //   Toast.makeText(this, "All permissions are granted", Toast.LENGTH_LONG).show()
            val sentPI: PendingIntent = PendingIntent.getBroadcast(context, 0, Intent("SMS_SENT"),
                PendingIntent.FLAG_IMMUTABLE)
            SmsManager.getDefault().sendTextMessage("+998915009894", null," binding.smsText.text.toString()", sentPI, null)
        } else {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri =
                Uri.fromParts("package", context.packageName, null)
            intent.data = uri
            context.startActivity(intent)
        }
    }
}