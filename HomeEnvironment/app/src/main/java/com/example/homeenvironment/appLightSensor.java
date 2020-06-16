package com.example.homeenvironment;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.SENSOR_SERVICE;
import static android.hardware.Sensor.TYPE_LIGHT;

public class appLightSensor {
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightEventListener;
    private float lightQuantity;
    private View view;
    TextView luxText;

    public appLightSensor(View view){
        this.view = view;
        sensorManager = (SensorManager) view.getContext().getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(TYPE_LIGHT);
        luxText = (TextView) view.findViewById(R.id.Lux_Measurement);

        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                lightQuantity = sensorEvent.values[0];
            }

            //Denne funktion skal være der for SensorEventListeners
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_UI);
    }
    public float getLux(){
        if(lightSensor == null) Toast.makeText(view.getContext(), "This phone doesn't have a lightsensor", Toast.LENGTH_SHORT).show();
        return lightQuantity;


    }


}


