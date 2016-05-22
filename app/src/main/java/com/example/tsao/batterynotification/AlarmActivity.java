package com.example.tsao.batterynotification;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ImageView IV = (ImageView) findViewById(R.id.im1);
        IV.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.hii));
    }
}
