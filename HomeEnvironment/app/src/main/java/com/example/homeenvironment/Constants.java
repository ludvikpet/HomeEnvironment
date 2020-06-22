package com.example.homeenvironment;

import android.app.Activity;
import android.view.View;

import androidx.fragment.app.Fragment;

public class Constants {
    private int MAIN_ACTIVITY_CALL = 101;
    private int TIP_ACTIVITY_CALL = 202;
    private int SETTINGS_ACTIVITY_CALL = 303;
    private Fragment fragment;
    private Activity activity;
    private static Constants instance;
    private View view;

    private Constants() {
    }

    public static Constants getInstance() {
        if(instance == null) {
            instance = new Constants();
        }
        return instance;
    }

    public static void deleteInstance() {
        instance = null;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
