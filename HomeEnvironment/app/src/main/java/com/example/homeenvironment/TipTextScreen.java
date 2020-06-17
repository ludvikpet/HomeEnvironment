package com.example.homeenvironment;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class TipTextScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tiptextscreen);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigt = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (heigt * .6));

    }
}
