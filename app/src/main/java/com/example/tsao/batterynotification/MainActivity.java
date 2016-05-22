package com.example.tsao.batterynotification;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button set_bt;
    BroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set_bt = (Button) findViewById(R.id.set);
        set_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        receiver = new batteryReceiver();
        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver, ifilter);
    }
    @Override
    protected void onPause(){
        super.onPause();
        unregisterReceiver(receiver);
    }

}
