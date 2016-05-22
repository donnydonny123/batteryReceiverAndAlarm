package com.example.tsao.batterynotification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.test.suitebuilder.annotation.Suppress;
import android.widget.Toast;

public class batteryReceiver extends BroadcastReceiver {
    public batteryReceiver() {
    }
    @SuppressLint("NewApi")
    private void SendNotifocation(Context context, int level){
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("剩餘電量");
        builder.setContentText(""+level);
        builder.setTicker("剩餘電量:"+level);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Notification notify = builder.build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,notify);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_BATTERY_CHANGED)){
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0); //"level"
            if (level%10 == 0) {
                Toast.makeText(context, "剩餘電量:" + level, Toast.LENGTH_SHORT).show();
                SendNotifocation(context, level);
            }
        }
    }
}
