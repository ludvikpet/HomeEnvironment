package com.example.homeenvironment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import static com.example.homeenvironment.R.*;


public class InfoTextScreen extends Activity {

    private String tempInfo = "TEMPORARY";
    private String pressureInfo = "TEMPORARY2";
    private String lightInfo = "TEMPORARY3";
    private String humidityInfo = "TEMPORARY4";
    private String noiseInfo = "TEMPORARY5";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(layout.activity_tiptextscreen);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigt = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (heigt * .6));

        TextView infoText = (TextView) findViewById(id.textview_the_tip);
        if(infoText != null){
            Intent intent = getIntent();
            String type = intent.getStringExtra("infoType");

            if(type.equals("temp")) {
                infoText.setText(tempInfo);
            } else if(type.equals("pressure")){
                infoText.setText(pressureInfo);
            } else if(type.equals("light")){
                infoText.setText(lightInfo);
            } else if(type.equals("humidity")){
                infoText.setText(humidityInfo);
            } else if(type.equals("noise")) {
                infoText.setText(noiseInfo);
            }
        }
    }
}
