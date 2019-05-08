package com.example.myapplication.sms;

public class SmsViewData {
    public final String messageBody;
    public final String sender;
    public final int total;
    public final int index;

    public SmsViewData(String messageBody, String sender, int total, int index) {
        this.messageBody = messageBody;
        this.sender = sender;
        this.total = total;
        this.index = index;
    }
}
