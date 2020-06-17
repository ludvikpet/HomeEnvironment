package com.example.homeenvironment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.util.Log;
import android.widget.Toast;


import com.example.homeenvironment.Sensors.AppTemperatureSensor;

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
                    String tempBoolean = getString(R.string.tempBoolean);

                    if (tempPref.isChecked()) {
                        tempPref.setChecked(false);
                        tempBoolean = "false";

                    } else if (!tempPref.isChecked()) {
                        tempPref.setChecked(true);
                        tempBoolean = "true";
                    }

                    return false;
                }
            });
            //Notifications switch
            notificationsPref = (android.preference.SwitchPreference) findPreference("notifications");
            notificationsPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener()
            {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    String notificationBoolean = getString(R.string.notiBoolean);
                    if (notificationsPref.isChecked()) {
                        notificationBoolean = "false";
                        notificationsPref.setChecked(false);
                    } else if (!notificationsPref.isChecked()) {
                        notificationBoolean = "true";
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
