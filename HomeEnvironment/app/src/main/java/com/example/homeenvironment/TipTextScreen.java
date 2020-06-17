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


public class TipTextScreen extends Activity {

    private String tempTip = "The ideal room temperature in an office space is around 22C or 72F. If your room is warmer than this, consider opening a few windows or using a fan. If your room is colder you might want to consider moving closer to a heat source and not opening any windows for extended periods of time.";
    private String pressureTip = "The ideal level of pressure in an area fit for office work is around 985 Pa. If your area consistently has lower pressure, you might want to consider moving to a lower altitude when you work.";
    private String lightTip = "The ideal light level for your working space should be around 320-500 lx. Is it much brighter, consider rolling down some blinds or going to a more shaded area. Is it darker, turn on some lights or ideally you can get some sunlight by opening up your blinds.";
    private String humidityTip = "Ideally the humidity in your area should be around 45% - 55%. If the humidity deviates from this, you should look into getting a humidifier that can regulate your humidity level.";
    private String noiseTip = "The ideal noise level in your workspace should never exceed 60 dB. If the noise level of your working environment is consistently higher than 60 dB, consider moving to a quieter workspace or ask the people around you to quiet down.";

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

        TextView tipText = (TextView) findViewById(id.textview_the_tip);
        if(tipText != null){
            Intent intent = getIntent();
            String type = intent.getStringExtra("tipType");

            if(type.equals("temp")) {
                tipText.setText(tempTip);
            } else if(type.equals("pressure")){
                tipText.setText(pressureTip);
            } else if(type.equals("light")){
                tipText.setText(lightTip);
            } else if(type.equals("humidity")){
                tipText.setText(humidityTip);
            } else if(type.equals("noise")) {
                tipText.setText(noiseTip);
            }
            }

    }
}
