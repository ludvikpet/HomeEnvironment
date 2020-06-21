package com.example.homeenvironment;

import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import androidx.annotation.RequiresApi;

import com.example.homeenvironment.Alarm.AlarmCreateActivity;
import com.example.homeenvironment.Sensors.AppTemperatureSensor;

public class Settings extends PreferenceActivity {

    public static SwitchPreference tempPref;
    public static SwitchPreference notificationsPref;
    public static ListPreference timeInterval;
    private final static String TAG = "Settings";
    public static boolean notificationPrefChanged;


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
            notificationPrefChanged = false;

    }


        @Override
        public void onResume() {
            super.onResume();

            timeInterval = (android.preference.ListPreference) findPreference("time_interval");
            timeInterval.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    timeInterval.setSummary((String) newValue);
                    timeInterval.setValue(newValue.toString());
                    notificationPrefChanged = true;
                    return true;
                }
            });

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
                        notificationPrefChanged = true;
                        notificationsPref.setChecked(false);

                    } else if (!notificationsPref.isChecked()) {
                        notificationPrefChanged = false;
                        notificationsPref.setChecked(true);
                    }

                    return false;
                }

            });
        }

        }
}
