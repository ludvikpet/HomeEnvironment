package com.example.homeenvironment;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {


    private static final String TAG = "AlarmReceiver";
    // Notification ID to allow for future updates
    private static final int NOTIFICATION_ID = 1;

    // Notification Text Elements
    private final CharSequence tickerText = "Hello";
    private final CharSequence contentTitle ="HomeEnvironment";
    private final CharSequence contentText = "it is a long time since, you have check your home Environment";

    // Notification Action Elements
    private Intent notificationIntent;
    private PendingIntent contentIntent;

    // Notification Sound and Vibration on Arrival
    private final long[] vibratePattern = {100, 100, 100, 400, 400, 100, 100, 100};

    public AlarmReceiver() {
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        // Log receipt of the Intent with timestamp
        Log.i(TAG,DateFormat.getDateTimeInstance().format(new Date()));
        Toast.makeText(context,"hello!!!!!!!!!",Toast.LENGTH_LONG).show();
// The Intent to be used when the user clicks on the Notification View
        notificationIntent = new Intent(context, FirstFragment.class);

        // The PendingIntent that wraps the underlying Intent
        contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        // Build the Notification
        Notification.Builder notificationBuilder = new Notification.Builder(
                context).setTicker(tickerText)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setAutoCancel(true).setContentTitle(contentTitle)
                .setContentText(contentText).setContentIntent(contentIntent)
                .setSound(uri).setVibrate(vibratePattern);

        // Get the NotificationManager
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        // Pass the Notification to the NotificationManager:
        mNotificationManager.notify(NOTIFICATION_ID,
                notificationBuilder.build());

        // Log occurence of notify() call
        Log.i(TAG, "Sending notification at:"
                + DateFormat.getDateTimeInstance().format(new Date()));

    }

}
