package com.example.homeenvironment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
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


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        // Log receipt of the Intent with timestamp
        Log.i(TAG,DateFormat.getDateTimeInstance().format(new Date()));
        Toast.makeText(context,"hello!!!!!!!!!",Toast.LENGTH_LONG).show();


    }

}
