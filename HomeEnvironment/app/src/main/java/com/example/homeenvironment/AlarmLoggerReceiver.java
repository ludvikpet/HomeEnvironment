package com.example.homeenvironment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.DateFormat;
import java.util.Date;

public class AlarmLoggerReceiver extends BroadcastReceiver {
    private static final String TAG = "AlarmLoggerReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {

        // Log receipt of the Intent with timestamp
        Log.i(TAG,DateFormat.getDateTimeInstance().format(new Date()));

    }

}
