package com.example.myapplication.sms;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SmsViewModel extends ViewModel {

    private MutableLiveData<SmsViewData> smsViewData;

    public LiveData<SmsViewData> getSmsData() {
        if (smsViewData == null) {
            smsViewData = new MutableLiveData<>();
        }
        return smsViewData;
    }

    public void updateSmsViewData(SmsViewData smsViewData) {
        this.smsViewData.setValue(smsViewData);
    }
}
