package com.example.homeenvironment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.homeenvironment.Sensors.AppTemperatureSensor;

import java.time.Instant;

public class Settings extends PreferenceActivity {

    public static SwitchPreference tempPref;
    public static SwitchPreference notificationsPref;

    private final static String TAG = "Settings";
    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
            // Implementation
        }
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment {

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.root_preferences);

            // Temperature switch
            tempPref = (android.preference.SwitchPreference) findPreference("temperature");
            tempPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    //Temp switch
                    String notification_bool = getString(R.string.notification_Boolean);
                    if (tempPref.isChecked()) {
                        tempPref.setChecked(false);
                        AppTemperatureSensor.temperatureMode= "false";
                    } else {
                        tempPref.setChecked(true);
                        AppTemperatureSensor.temperatureMode = "true";
                    }

                    return false;
                }
            });
            //Notifications switch
            notificationsPref = (android.preference.SwitchPreference) findPreference("notifications");
            notificationsPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                                 

                    if (notificationsPref.isChecked()) {

                        notificationsPref.setChecked(false);

                    } else if (!notificationsPref.isChecked()) {

                        notificationsPref.setChecked(true);
                    }

                    return false;
                }

            });
        }
    }


        @Override
        public void onResume() {
            super.onResume();
/**
 SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);
 boolean test = preferences.getBoolean("test", false);

 if (test) {
 testPref.setSummary("Enabled");
 } else {
 testPref.setSummary("Disabled");
 }
 */
        }
    }
