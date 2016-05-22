package com.example.tsao.batterynotification;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.suitebuilder.annotation.Suppress;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button set_bt, cancel_bt;
    TimePicker tp;
    BroadcastReceiver BR_receiver;
    AlarmManager am;
    int hr, min;
    int req = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hr = 0; min = 0;
        req = 0;
        set_bt = (Button) findViewById(R.id.set);
        set_bt.setOnClickListener(new OnClickListener() {
            @Override
            @SuppressLint("NewApi")
            public void onClick(View v) {
                hr = tp.getHour();
                min = tp.getMinute();
                Log.d("MAIN","BUTTON SET CLICKED "+hr+":"+min);
                Toast.makeText(v.getContext(), "鬧鐘:"+hr+":"+min+"已設定!", Toast.LENGTH_SHORT).show();;
                Intent it = new Intent();
                it.setClass(MainActivity.this, AlarmReceiver.class);
                it.putExtra("TIME",hr+":"+min);
                PendingIntent PI = PendingIntent.getBroadcast(MainActivity.this, ++req, it, PendingIntent.FLAG_UPDATE_CURRENT);
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(System.currentTimeMillis());
                c.set(Calendar.HOUR_OF_DAY, hr);
                c.set(Calendar.MINUTE, min);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), PI);
            }
        });
        cancel_bt = (Button) findViewById(R.id.cancel);
        cancel_bt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MAIN","BUTTON CANCEL CLICKED");
            }
        });
        tp = (TimePicker) findViewById(R.id.time_picker);
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hr = hourOfDay;
                min = minute;
            }
        });
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
        BR_receiver = new batteryReceiver();
        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(BR_receiver, ifilter);
    }
    @Override
    protected void onPause(){
        super.onPause();
        unregisterReceiver(BR_receiver);
    }

}
