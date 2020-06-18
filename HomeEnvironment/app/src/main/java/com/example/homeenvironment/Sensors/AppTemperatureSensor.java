package com.example.homeenvironment.Sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Toast;

import static android.content.Context.SENSOR_SERVICE;
import static android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE;
import static android.hardware.Sensor.TYPE_LIGHT;

public class AppTemperatureSensor {
    public static String temperatureMode = "Unchanged";
    private SensorManager sensorManager;
    private Sensor temperatureSensor;
    private SensorEventListener temperatureEventListener;
    private float temperature;
    private View view;

    public AppTemperatureSensor(View view) {
        this.view = view;
        sensorManager = (SensorManager) view.getContext().getSystemService(SENSOR_SERVICE);
        temperatureSensor = sensorManager.getDefaultSensor(TYPE_AMBIENT_TEMPERATURE);

        temperatureEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                temperature = sensorEvent.values[0];
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(temperatureEventListener, temperatureSensor, SensorManager.SENSOR_DELAY_UI);
    }

    public float getTemperature(){
        if(temperatureSensor == null) Toast.makeText(view.getContext(), "This phone doesn't have a temperature sensor", Toast.LENGTH_SHORT).show();

        if(temperatureMode == "true"){
            return (float) (temperature * 1.8 + 32);
        }
        else if(temperatureMode == "false"){
            return temperature-32 * 5/9;
        }
        else{
            return temperature;
        }

    }
}
