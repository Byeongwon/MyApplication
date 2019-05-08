package com.example.myapplication.sms;

public class Sms {
    public final String messageBody;
    public final String sender;
    public final boolean isRead;

    public Sms(String messageBody, String sender) {
        this.messageBody = messageBody;
        this.sender = sender;
        this.isRead = false;
    }
}
