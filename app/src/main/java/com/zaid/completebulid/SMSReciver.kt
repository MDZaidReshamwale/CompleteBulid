package com.zaid.completebulid


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import android.widget.Toast
import android.os.Build




/**
 * complete the task within 5 secs
 */
class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Toast.makeText(context,"sms receiver",Toast.LENGTH_SHORT).show()
        /*showSmsBody(intent,context);
        //check if the sms starts with the password if yes
        var intent = Intent(context,MyService::class.java)
        intent.putExtra("fromphno","5554")
        context.startService(intent)*/
    }

    private fun showSmsBody(intent: Intent, context: Context) {
        var  bundle: Bundle
        var  currentSMS: SmsMessage
        var  message: String
        if (intent.action.equals("android.provider.Telephony.SMS_RECEIVED")) {
            bundle = intent.extras!!
            if (bundle != null) {
                val pdu_Objects = bundle.get("pdus") as Array<Any>
                if (pdu_Objects != null) {
                    for (aObject in pdu_Objects) {
                        currentSMS = getIncomingMessage(aObject, bundle)
                        val senderNo: String = currentSMS.getDisplayOriginatingAddress()
                        message = currentSMS.getDisplayMessageBody()
                        Toast.makeText(context,
                            "senderNum: $senderNo :\n message: $message",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    // abortBroadcast()
                    // End of loop
                }
            }
        }
    }

    private fun getIncomingMessage(aObject: Any, bundle: Bundle): SmsMessage {
        val currentSMS: SmsMessage
        currentSMS = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val format = bundle.getString("format")
            SmsMessage.createFromPdu(aObject as ByteArray, format)
        } else {
            SmsMessage.createFromPdu(aObject as ByteArray)
        }
        return currentSMS
    }
}