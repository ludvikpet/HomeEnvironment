package com.example.homeenvironment;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import java.text.DateFormat;
import java.util.Date;

public class AlarmCreateActivity {
    private static final long REPEAT_INTERVAL =60*1000L ;
    private AlarmManager alarmManager;
    private PendingIntent loggerReceiverPendingIntent;


    public AlarmCreateActivity (View view){

        Intent loggerReceiverIntent = new Intent(view.getContext(),
                AlarmLoggerReceiver.class);
        Log.i("AlarmCreateActivity", "hey");
        // Create PendingIntent that holds the mLoggerReceiverPendingIntent
        loggerReceiverPendingIntent = PendingIntent.getBroadcast( view.getContext(), 0, loggerReceiverIntent, 0);

    }

    public void reset(){

      alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
              SystemClock.elapsedRealtime(), REPEAT_INTERVAL,
              loggerReceiverPendingIntent);
  }
}
