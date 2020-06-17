package com.example.homeenvironment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class AlarmCreateActivity {
    private static final long REPEAT_INTERVAL =60*1000L ;
    private AlarmManager alarmManager;
    private PendingIntent receiverPendingIntent;
    private View view;

    public AlarmCreateActivity (View view){
        this.view = view;
       Intent alarmIntent = new Intent(view.getContext(),AlarmReceiver.class);
        receiverPendingIntent = PendingIntent.getBroadcast( view.getContext(), 0, alarmIntent, 0);
    }

    public void setRepeating(){

        if(alarmManager != null){
            alarmManager.cancel(receiverPendingIntent);
            Log.i("Alarm", "AlarmCreateActivity her! has cancel alarm ");
        }

        alarmManager =(AlarmManager) view.getContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),50000,receiverPendingIntent);
        Log.i("Alarm", "AlarmCreateActivity her! has set repeating ");
    }
}
