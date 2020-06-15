package com.example.homeenvironment;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import static android.content.Context.SENSOR_SERVICE;
import static android.hardware.Sensor.TYPE_LIGHT;

public class FirstFragment extends Fragment {
    private appLightSensor mLightSensor;
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightEventListener;
    private float maxValue;
    private float lightQuantity;
    TextView luxText;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sensorManager = (SensorManager) view.getContext().getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(TYPE_LIGHT);
        luxText = (TextView) view.findViewById(R.id.Lux_Measurement);


        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                Toast.makeText(getContext(), "sensor changed", Toast.LENGTH_SHORT).show();
                lightQuantity = sensorEvent.values[0];
            }

            //Denne funktion skal være der for SensorEventListeners
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);

        view.findViewById(R.id.MeasuringLightButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lightSensor == null) {
                    Toast.makeText(view.getContext(), "This phone doesn't have a lightsensor", Toast.LENGTH_SHORT).show();
                } else {
                    luxText.setText(" This is the current lux " + lightQuantity);
                }

                //luxText.getText();
                //Log.i("MainActivity","This is the text of the textview " + luxText.getText());
                //luxText.setText("This is the current lux " + lightQuantity);

            }
        });
    }
}