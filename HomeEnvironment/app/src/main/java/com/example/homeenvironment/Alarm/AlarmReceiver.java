package com.example.homeenvironment.Alarm;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.homeenvironment.MainActivity;
import com.example.homeenvironment.R;

import java.text.DateFormat;
import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {


    private static final String TAG = "AlarmReceiver";
    private static final int NOTIFICATION_ID = 1;
    private final CharSequence contentTitle ="HomeEnvironment";
    private final CharSequence contentText = "it is a long time since, you have check your home Environment";
    private Intent notificationIntent;
    private PendingIntent contentIntent;
    private final long[] vibratePattern = {100, 100, 100, 400, 400, 100, 100, 100};
    private Uri uri;
    private NotificationManager notificationManager;

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {

        // When user clicks on the notification the following intent is used
        notificationIntent = new Intent(context, MainActivity.class);

        contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
        // Set the notifications ringtone to the smartphone default
        uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        // Building the Notification
        Notification.Builder notificationBuilder = new Notification.Builder(
                context).setSmallIcon(R.drawable.ic_stat_name)
                .setAutoCancel(true).setContentTitle(contentTitle)
                .setContentText(contentText).setContentIntent(contentIntent)
                .setSound(uri).setVibrate(vibratePattern);

        notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        // Sends the notification
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
        Log.i(TAG, " has Received Alarm: " + DateFormat.getDateTimeInstance().format(new Date()));

    }

}
