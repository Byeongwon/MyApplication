package com.example.myapplication.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsBroadcastReceiver extends BroadcastReceiver {
    public static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        Toast.makeText(context, "메시지가 도착했습니다.", Toast.LENGTH_SHORT).show();

        Sms smsMessage = getSmsMessage(intent);

        SmsRepository.getInstance().addMessage(smsMessage);
    }

    /**
     * Receiver를 등록하는 메소드
     */
    public void register(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SMS_RECEIVED);
        context.registerReceiver(this, intentFilter);
    }

    /**
     * Receiver를 해제하는 메소드
     */
    public void unRegister(Context context) {
        context.unregisterReceiver(this);
    }

    /**
     * Protocol Data Unit(pdu)형태로 데이터를 가져오는 메소드
     */
    private Sms getSmsMessage(Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();

            Object[] pdus = (Object[]) bundle.get("pdus");

            Sms message = changePduToMessage(pdus);
            return message;
        }
        return null;
    }

    private Sms changePduToMessage(Object[] pdus) {
        SmsMessage[] messages = new SmsMessage[pdus.length];

        for (int index = 0; index < messages.length; index++) {
            messages[index] = SmsMessage.createFromPdu((byte[]) pdus[index]);
        }

        String messageBody = messages[0].getMessageBody();
        String sender = messages[0].getDisplayOriginatingAddress();

        return new Sms(messageBody, sender);
    }

//    private boolean isSmsMessageEmpty(SmsMessage message) {
//        return CommonUtils.isEmpty(message);
//    }
}
