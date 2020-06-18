package com.example.homeenvironment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import com.example.homeenvironment.MainActivity;
public class AlarmCreateActivity{
    private static final long REPEAT_INTERVAL =60*1000L ;
    private AlarmManager alarmManager;
    private PendingIntent receiverPendingIntent;
    private View view;
    private SharedPreferences sharedPreferences;


    public AlarmCreateActivity (View view){
        this.view = view;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
       Intent alarmIntent = new Intent(view.getContext(),AlarmReceiver.class);
        receiverPendingIntent = PendingIntent.getBroadcast( view.getContext(), 0, alarmIntent, 0);

        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                Log.i("Alarm", ""+s);

                Log.i("Alarm", sharedPreferences.getBoolean(s,false)+"");
                if(s.equals("notifications")){
                    if(sharedPreferences.getBoolean(s,false)){
                        startAlarmNotification();
                    }else{
                        cancelAlarmNotification();
                    }
                }

            }
        });
    }
   

    private void startAlarmNotification(){
        alarmManager =(AlarmManager) view.getContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_FIFTEEN_MINUTES,receiverPendingIntent);
        Log.i("Alarm", "AlarmCreateActivity her! has set repeating ");
    }
    private void cancelAlarmNotification(){
        if(alarmManager != null){
            alarmManager.cancel(receiverPendingIntent);
            Log.i("Alarm", "AlarmCreateActivity her! has cancel alarm ");
        }
    }
    public void resetAlarmNotification(){
        if(sharedPreferences.getBoolean("notifications",false)){
            cancelAlarmNotification();
            startAlarmNotification();
        }
    }

}
