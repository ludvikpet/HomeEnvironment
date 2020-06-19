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

//                Log.i("Alarm", sharedPreferences.getBoolean(s,false)+"");
                if(s.equals("notifications")){
                    if(sharedPreferences.getBoolean(s,false)){
                        startAlarmNotification();
                        Log.i("Alarm", "Restarted alarm");
                    }else{
                        Log.i("Alarm", "Cancelled alarm");
                        cancelAlarmNotification();
                    }
                }

            }
        });


    }
   

    private void startAlarmNotification(){
        alarmManager =(AlarmManager) view.getContext().getSystemService(Context.ALARM_SERVICE);

        if (sharedPreferences.getString("time_interval", "halv time").equals("30 min")) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), receiverPendingIntent);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_HALF_HOUR / 120,receiverPendingIntent);
            Log.i("Alarm", "AlarmCreateActivity her! has set repeating ----> 0.5t");
        }
        else if(sharedPreferences.getString("time_interval", "halv time").equals("60 min")) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), receiverPendingIntent);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_HOUR / 60,receiverPendingIntent);
            Log.i("Alarm", "AlarmCreateActivity her! has set repeating ----> 1t");
        }
        else if(sharedPreferences.getString("time_interval", "halv time").equals("120 min")) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_HOUR * 2,receiverPendingIntent);
            Log.i("Alarm", "AlarmCreateActivity her! has set repeating ----> 2t");
        }
        else if(sharedPreferences.getString("time_interval", "halv time").equals("180 min")) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_HOUR * 3,receiverPendingIntent);
            Log.i("Alarm", "AlarmCreateActivity her! has set repeating ----> 3t");
        }
        else if(sharedPreferences.getString("time_interval", "halv time").equals("240 min")) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_HOUR * 4,receiverPendingIntent);
            Log.i("Alarm", "AlarmCreateActivity her! has set repeating ----> 4t");
        }
        else if(sharedPreferences.getString("time_interval", "halv time").equals("300 min")) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_HOUR * 5,receiverPendingIntent);
            Log.i("Alarm", "AlarmCreateActivity her! has set repeating ----> 5t");
        }
        else if(sharedPreferences.getString("time_interval", "halv time").equals("1440 min")) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_DAY,receiverPendingIntent);
            Log.i("Alarm", "AlarmCreateActivity her! has set repeating ----> 24t");
        }

        Log.i("Alarm", "AlarmCreateActivity her! has set repeating ----->" + sharedPreferences.getString("time_interval", "halv time"));
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
