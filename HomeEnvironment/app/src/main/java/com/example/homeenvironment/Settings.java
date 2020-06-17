package com.example.homeenvironment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

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

                    if (tempPref.isChecked()) {
                        Toast.makeText(getView().getContext(), "That worked! it's on!", Toast.LENGTH_SHORT);
                        tempPref.setChecked(false);

                        Log.i(TAG, "that's OFF");
                    } else if (!tempPref.isChecked()) {
                        Log.i(TAG, "That's ON");
                        Toast.makeText(getView().getContext(), "That worked! it's off!", Toast.LENGTH_SHORT);
                        tempPref.setChecked(true);
                    }

                    return false;
                }
            });
            //Notifications switch
            notificationsPref = (android.preference.SwitchPreference) findPreference("notifications");
            notificationsPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {

                    if (notificationsPref.isChecked()) {
                        Toast.makeText(getView().getContext(), "That worked! it's on!", Toast.LENGTH_SHORT);
                        notificationsPref.setChecked(false);
                        Log.i(TAG, "that's OFF");
                    } else if (!notificationsPref.isChecked()) {
                        Log.i(TAG, "That's ON");
                        Toast.makeText(getView().getContext(), "That worked! it's off!", Toast.LENGTH_SHORT);
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
