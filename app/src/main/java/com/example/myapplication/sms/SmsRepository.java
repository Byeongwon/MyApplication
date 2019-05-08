package com.example.myapplication.sms;

import java.util.ArrayList;
import java.util.List;

public class SmsRepository {

    private static SmsRepository smsRepository;
    private static List<Sms> smsMessages;

    private SmsRepository(){}

    public static SmsRepository getInstance() {
        if (smsRepository == null) {
            smsRepository = new SmsRepository();
            smsMessages = new ArrayList<>();
        }
        return smsRepository;
    }

    public void addMessage(Sms message) {
        smsMessages.add(message);
    }

    public List<Sms> getSmsMessages() {
        return smsMessages;
    }
}
