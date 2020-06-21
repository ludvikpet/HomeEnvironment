package com.example.homeenvironment.tips;

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

    TextView tipText;
    String type;
    ConstraintLayout tipLayout;
    int width, height;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);



        setContentView(layout.activity_tiptextscreen);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        width = dm.widthPixels;
        height = dm.heightPixels;

        tipText = (TextView) findViewById(id.textview_the_tip);

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
        switch (type) {
            case "tempTip":
                getWindow().setLayout((int) (width * .8), (int) (height * .72));
                tipText.setText(string.tempTipText);
                break;
            case "pressureTip":
                tipText.setText(string.pressureTipText);
                break;
            case "lightTip":
                tipText.setText(string.lightTipText);
                break;
            case "humidityTip":
                getWindow().setLayout((int) (width * .8), (int) (height * .6));
                tipText.setText(string.humidityTipText);
                break;
            case "noiseTip":
                tipText.setText(string.noiseTipText);
                break;
        }
    }

    private void getTheInfo(String type) {
        switch (type) {
            case "tempInfo":
                tipText.setText(string.tempInfoText);
                break;
            case "pressureInfo":
                tipText.setText(string.pressureInfoText);
                break;
            case "lightInfo":
                tipText.setText(string.lightInfoText);
                break;
            case "humidityInfo":
                tipText.setText(string.humidityInfoText);
                break;
            case "noiseInfo":
                tipText.setText(string.noiseInfoText);
                break;
        }
    }
}
