package com.example.homeenvironment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import static com.example.homeenvironment.R.*;


public class TipTextScreen extends Activity {

    private String tempTip = "The ideal room temperature in an office space is around 22C or 72F. If your room is warmer than this, consider opening a few windows or using a fan. If your room is colder you might want to consider moving closer to a heat source and not opening any windows for extended periods of time.";
    private String pressureTip = "The ideal level of pressure in an area fit for office work is around 985 Pa. If your area consistently has lower pressure, you might want to consider moving to a lower altitude when you work.";
    private String lightTip = "The ideal light level for your working space should be around 320-500 lx. Is it much brighter, consider rolling down some blinds or going to a more shaded area. Is it darker, turn on some lights or ideally you can get some sunlight by opening up your blinds.";
    private String humidityTip = "Ideally the humidity in your area should be around 45% - 55%. If the humidity deviates from this, you should look into getting a humidifier that can regulate your humidity level.";
    private String noiseTip = "The ideal noise level in your workspace should never exceed 60 dB. If the noise level of your working environment is consistently higher than 60 dB, consider moving to a quieter workspace or ask the people around you to quiet down.";

    private String tempInfo = "Ideal temperature is 22C/72F ";
    private String pressureInfo = "Ideal pressure is 985 Pa";
    private String lightInfo = "Ideal lux is 320-500 lx";
    private String humidityInfo = "Ideal humidity is 45% - 55%";
    private String noiseInfo = "Ideal noise level is >60 dB";

    TextView tipText;
    String type;
    ConstraintLayout tipLayout;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);



        setContentView(layout.activity_tiptextscreen);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        tipText = (TextView) findViewById(id.textview_the_tip);

        tipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tipLayout = (ConstraintLayout) findViewById(id.tiptextscreen_layout);

        tipLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        if(tipText != null){
            Intent intent = getIntent();
            String decider = intent.getStringExtra("info/tip");

            if(decider.equals("tip")) {
                type = intent.getStringExtra("tipType");
                getWindow().setLayout((int) (width * .8), (int) (height * .5));
                getTheTip(type);
            } else {
                type = intent.getStringExtra("infoType");
                getWindow().setLayout((int) (width * .8), (int) (height * .15));
                getTheInfo(type);
            }
        }
    }


    private void getTheTip(String type) {
        if(type.equals("tempTip")) {
            tipText.setText(tempTip);
        } else if(type.equals("pressureTip")){
            tipText.setText(pressureTip);
        } else if(type.equals("lightTip")){
            tipText.setText(lightTip);
        } else if(type.equals("humidityTip")){
            tipText.setText(humidityTip);
        } else if(type.equals("noiseTip")) {
            tipText.setText(noiseTip);
        }
    }

    private void getTheInfo(String type) {
        if(type.equals("tempInfo")) {
            tipText.setText(tempInfo);
        } else if(type.equals("pressureInfo")){
            tipText.setText(pressureInfo);
        } else if(type.equals("lightInfo")){
            tipText.setText(lightInfo);
        } else if(type.equals("humidityInfo")){
            tipText.setText(humidityInfo);
        } else if(type.equals("noiseInfo")) {
            tipText.setText(noiseInfo);
        }
    }
}
