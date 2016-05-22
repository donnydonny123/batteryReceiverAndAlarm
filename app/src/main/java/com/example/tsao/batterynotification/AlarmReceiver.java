package com.example.tsao.batterynotification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }
    @SuppressLint("NewApi")
    private void SendNotification(Context context, String time){
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("鬧鐘...");
        builder.setContentText(time);
        builder.setTicker("鬧鐘at"+time);
        builder.setSmallIcon(R.mipmap.ic_launcher);

        Intent it = new Intent();
        it.setClass(context, AlarmActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, it, 0);
        builder.setContentIntent(pi);

        Notification.BigPictureStyle bps = new Notification.BigPictureStyle();
        bps.bigPicture(BitmapFactory.decodeResource(context.getResources(), R.drawable.hii));
        builder.setStyle(bps);

        Notification notify = builder.build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2,notify);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String time = intent.getStringExtra("TIME");
        Log.d("RECEIVER",time);
        Toast.makeText(context,time + " 時間到", Toast.LENGTH_SHORT).show();
        SendNotification(context, time);

        Intent it = new Intent();
        it.setClass(context, AlarmActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(it);
    }
}
