package com.example.homeenvironment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class Settings extends PreferenceActivity {

    public static SwitchPreference testPref;

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

    public static class MyPreferenceFragment extends PreferenceFragment
    {

        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.root_preferences);

            testPref = (android.preference.SwitchPreference) findPreference("test");
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("test")) {
            boolean test = sharedPreferences.getBoolean("test", false);
            //Do whatever you want here. This is an example.
            if (test) {
                testPref.setSummary("Enabled");
            } else {
                testPref.setSummary("Disabled");
            }
        }
    }

        @Override
        public void onResume () {
            super.onResume();

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);
            boolean test = preferences.getBoolean("test", false);

            if (test) {
                testPref.setSummary("Enabled");
            } else {
                testPref.setSummary("Disabled");
            }
        }
    }
