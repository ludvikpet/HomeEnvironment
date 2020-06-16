package com.example.homeenvironment;

import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.homeenvironment.Sensors.AppBarometerSensor;
import com.example.homeenvironment.Sensors.AppTemperatureSensor;
import com.example.homeenvironment.Sensors.appLightSensor;

public class FirstFragment extends Fragment {
    private appLightSensor mLightSensor;
    private AppBarometerSensor mBarometerSensor;
    private AppTemperatureSensor mTemperatureSensor;
    private SensorEventListener lightEventListener;
    private float maxValue;
    private float lightQuantity;
    private TextView luxText;
    private TextView pressureText;
    private TextView humidityText;
    private TextView temperatureText;

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
        mLightSensor = new appLightSensor(view);
        mBarometerSensor = new AppBarometerSensor(view);
        mTemperatureSensor = new AppTemperatureSensor(view);
        luxText = view.findViewById(R.id.Lux_Measurement);
        pressureText = view.findViewById(R.id.pressureSensorView);
        humidityText = view.findViewById(R.id.Humidity_Text);
        temperatureText = view.findViewById(R.id.Temperature_Text);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });


        view.findViewById(R.id.MeasuringLightButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    luxText.setText(" This is the current lux " + mLightSensor.getLux());
                    pressureText.setText("This is the current pressure " + mBarometerSensor.getPressure());
                    humidityText.setText("This is the current humidity " + mBarometerSensor.getHumidity());
                    temperatureText.setText("This is the current temperature " + mTemperatureSensor.getTemperature());


            }
        });
    }
}