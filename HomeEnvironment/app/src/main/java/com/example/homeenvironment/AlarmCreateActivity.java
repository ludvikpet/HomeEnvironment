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
    private AlarmManager alarmManager;
    private PendingIntent receiverPendingIntent;
    private View view;
    private Context context;
    private SharedPreferences sharedPreferences;
    private static long repeatInterval = AlarmManager.INTERVAL_HALF_HOUR;

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
                repeatInterval = setRepeatInterval();
                resetAlarmNotification();
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
    private  long setRepeatInterval(){
        String[] newInterval = sharedPreferences.getString("time_interval","every half hour").split(" ");
        Log.i("Alarm",""+ newInterval.length);
        String text = "";
        for(int i=0;i<newInterval.length;i++){
            text = text +" " + newInterval[i];
        }
        Log.i("Alarm", "" + text);
        if(newInterval[1].equals("half")){
           return AlarmManager.INTERVAL_HALF_HOUR;
        }else if(newInterval[1].equals("hour")){
            return AlarmManager.INTERVAL_HOUR;
        }else if(newInterval[1].equals("every")){
            return AlarmManager.INTERVAL_DAY;
        }else{
            return AlarmManager.INTERVAL_HOUR * Integer.parseInt(newInterval[1]);
        }

    }

    private void startAlarmNotification(){
        alarmManager =(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), receiverPendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),repeatInterval,receiverPendingIntent);

        Log.i("Alarm", "AlarmCreateActivity her! has set repeating ----->" + sharedPreferences.getString("time_interval", "every half hour"));
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
