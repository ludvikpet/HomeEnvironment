package com.example.homeenvironment.Sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.SENSOR_SERVICE;
import static android.hardware.Sensor.TYPE_LIGHT;
import static android.hardware.Sensor.TYPE_PRESSURE;
import static android.hardware.Sensor.TYPE_RELATIVE_HUMIDITY;

public class AppBarometerSensor {
    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private Sensor humiditySensor;
    private SensorEventListener pressureEventListener;
    private SensorEventListener humidityEventListener;
    private float pressureAmount;
    private float humidityAmount;
    private View view;


    public AppBarometerSensor(View view){
        this.view = view;
        sensorManager = (SensorManager) view.getContext().getSystemService(SENSOR_SERVICE);
        pressureSensor = sensorManager.getDefaultSensor(TYPE_PRESSURE);
        humiditySensor = sensorManager.getDefaultSensor(TYPE_RELATIVE_HUMIDITY);

        pressureEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                pressureAmount = sensorEvent.values[0];
            }

            //Denne funktion skal være der for SensorEventListeners
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        humidityEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                humidityAmount = sensorEvent.values[0];
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(pressureEventListener, pressureSensor, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(humidityEventListener,humiditySensor, SensorManager.SENSOR_DELAY_UI);
    }



    public int getPressure(){
        if(pressureSensor == null) Toast.makeText(view.getContext(),"Your phone doesn't have a pressure sensor", Toast.LENGTH_SHORT);
        return (int) pressureAmount;
    }
    public int getHumidity(){
        if(pressureSensor == null) Toast.makeText(view.getContext(),"Your phone doesn't have a pressure sensor", Toast.LENGTH_SHORT);
        return (int) humidityAmount;
    }
}
