package com.example.homeenvironment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.util.Log;
import android.widget.Toast;

<<<<<<< Updated upstream
=======
import com.example.homeenvironment.Sensors.AppTemperatureSensor;


>>>>>>> Stashed changes
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
                        Toast.makeText(getView().getContext(), "That worked! it's on!", Toast.LENGTH_SHORT);
                        tempPref.setChecked(false);
<<<<<<< Updated upstream

                        Log.i(TAG, "that's OFF");
                    } else if (!tempPref.isChecked()) {
                        Log.i(TAG, "That's ON");
                        Toast.makeText(getView().getContext(), "That worked! it's off!", Toast.LENGTH_SHORT);
                        tempPref.setChecked(true);
=======
                        tempBoolean = "false";

                    } else {
                        tempPref.setChecked(true);
                        tempBoolean = "true";
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                        Toast.makeText(getView().getContext(), "That worked! it's on!", Toast.LENGTH_SHORT);
=======
                        notificationBoolean = "false";
>>>>>>> Stashed changes
                        notificationsPref.setChecked(false);
                        Log.i(TAG, "that's OFF");
                    } else if (!notificationsPref.isChecked()) {
<<<<<<< Updated upstream
                        Log.i(TAG, "That's ON");
                        Toast.makeText(getView().getContext(), "That worked! it's off!", Toast.LENGTH_SHORT);
=======
                        notificationBoolean = "true";
>>>>>>> Stashed changes
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
