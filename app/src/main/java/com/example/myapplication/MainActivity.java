package com.example.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.sms.Sms;
import com.example.myapplication.sms.SmsBroadcastReceiver;
import com.example.myapplication.sms.SmsRepository;
import com.example.myapplication.sms.SmsViewData;
import com.example.myapplication.sms.SmsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView1, textView2, textView3, textView4;
    SmsViewModel model;
    SmsBroadcastReceiver smsBroadcastReceiver = new SmsBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smsBroadcastReceiver.register(this);
        model = ViewModelProviders.of(this).get(SmsViewModel.class);

        button = (Button)findViewById(R.id.test1);
        textView1 = (TextView)findViewById(R.id.test2);
        textView2 = (TextView)findViewById(R.id.test3);
        textView3 = (TextView)findViewById(R.id.test4);
        textView4 = (TextView)findViewById(R.id.test5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsRepository smsRepository = SmsRepository.getInstance();
                List<Sms> sms = smsRepository.getSmsMessages();
                if (sms.isEmpty()) {
                    return;
                }
                Sms message = sms.get(0);
                SmsViewData temp = new SmsViewData(message.messageBody, message.sender, sms.size(), (sms.size() - sms.indexOf(message)));
                model.updateSmsViewData(temp);

            }
        });

        model.getSmsData().observe(this, new Observer<SmsViewData>() {
            @Override
            public void onChanged(@Nullable SmsViewData smsViewData) {
                updateSmsReadView(smsViewData);
            }
        });
    }

    public void updateSmsReadView(SmsViewData smsViewData) {
        textView1.setText(smsViewData.messageBody);
        textView2.setText(smsViewData.sender);
        textView3.setText(smsViewData.total+"");
        textView4.setText(smsViewData.index+"");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        smsBroadcastReceiver.unRegister(this);
    }
}
