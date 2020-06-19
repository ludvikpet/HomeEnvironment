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
    private Context context;
    private SharedPreferences sharedPreferences;

    private SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            Log.i("Alarm", "Key: "+s);

//                Log.i("Alarm", sharedPreferences.getBoolean(s,false)+"");
            if(s.equals("notifications")){
                if(sharedPreferences.getBoolean(s,false)){
                    resetAlarmNotification();
                    Log.i("Alarm", "Restarted alarm");
                }else{
                    Log.i("Alarm", "Cancelled alarm");
                    cancelAlarmNotification();
                }
            }
            if(s.equals("time_interval")) {
                if(sharedPreferences.getBoolean("notifications", false)) {
                    resetAlarmNotification();
                }
            }

        }
    };

    public AlarmCreateActivity (View view){
        this.view = view;
        context = view.getContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Intent alarmIntent = new Intent(view.getContext(),AlarmReceiver.class);
        receiverPendingIntent = PendingIntent.getBroadcast( context, 0, alarmIntent, 0);

        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);




    }

    public AlarmCreateActivity (PendingIntent pendingIntent, Context context){
        receiverPendingIntent = pendingIntent;
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
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
        alarmManager =(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (sharedPreferences.getString("time_interval", "halv time").equals("every half hour")) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), receiverPendingIntent);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_HALF_HOUR / 120,receiverPendingIntent);
            Log.i("Alarm", "AlarmCreateActivity her! has set repeating ----> 0.5t");
        }
        else if(sharedPreferences.getString("time_interval", "halv time").equals("every hour")) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), receiverPendingIntent);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_HOUR / 60,receiverPendingIntent);
            Log.i("Alarm", "AlarmCreateActivity her! has set repeating ----> 1t");
        }
        else if(sharedPreferences.getString("time_interval", "halv time").equals("every 2 hours")) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_HOUR * 2,receiverPendingIntent);
            Log.i("Alarm", "AlarmCreateActivity her! has set repeating ----> 2t");
        }
        else if(sharedPreferences.getString("time_interval", "halv time").equals("every 3 hours")) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_HOUR * 3,receiverPendingIntent);
            Log.i("Alarm", "AlarmCreateActivity her! has set repeating ----> 3t");
        }
        else if(sharedPreferences.getString("time_interval", "halv time").equals("every 4 hours")) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_HOUR * 4,receiverPendingIntent);
            Log.i("Alarm", "AlarmCreateActivity her! has set repeating ----> 4t");
        }
        else if(sharedPreferences.getString("time_interval", "halv time").equals("every 5 hours")) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_HOUR * 5,receiverPendingIntent);
            Log.i("Alarm", "AlarmCreateActivity her! has set repeating ----> 5t");
        }
        else if(sharedPreferences.getString("time_interval", "halv time").equals("once every day")) {
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
