package com.philliphsu.clock2;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class UpcomingAlarmReceiver extends BroadcastReceiver {
    public static final String ACTION_CANCEL_NOTIFICATION
            = "com.philliphsu.clock2.action.CANCEL_NOTIFICATION";
    public static final String ACTION_SHOW_SNOOZING
            = "com.philliphsu.clock2.action.CANCEL_NOTIFICATION";

    private static int count = -1;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (ACTION_CANCEL_NOTIFICATION.equals(intent.getAction())) {
            nm.cancel(count);
        } else if (ACTION_SHOW_SNOOZING.equals(intent.getAction())) {
            Notification note = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Snoozing")
                    .setContentText("New ring time here")
                    .setOngoing(true)
                    .build();
            // todo actions
            nm.notify(count, note);
        } else {
            Notification note = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Upcoming alarm")
                    .setContentText("Ring time here")
                    .setOngoing(true)
                    .build();
            // todo actions
            nm.notify(++count, note);
        }
    }
}