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
import com.example.homeenvironment.Sensors.AppLightSensor;

public class FirstFragment extends Fragment {
    private AppLightSensor mLightSensor;
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
        mLightSensor = new AppLightSensor(view);
        mBarometerSensor = new AppBarometerSensor(view);
        mTemperatureSensor = new AppTemperatureSensor(view);
        //luxText = view.findViewById(R.id.Lux_Measurement);
        //pressureText = view.findViewById(R.id.pressureSensorView);
        //humidityText = view.findViewById(R.id.Humidity_Text);
        //temperatureText = view.findViewById(R.id.Temperature_Text);
        luxText = view.findViewById(R.id.lightID);
        pressureText = view.findViewById(R.id.pressureID);
        humidityText = view.findViewById(R.id.humidityID);
        temperatureText = view.findViewById(R.id.temperatureID);
        final AlarmCreateActivity alarmCreateActivity = new AlarmCreateActivity(view);

        view.findViewById(R.id.tipsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });


        view.findViewById(R.id.measureButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    alarmCreateActivity.setRepeating();
                    luxText.setText("" + mLightSensor.getLux());
                    pressureText.setText("" + mBarometerSensor.getPressure());
                    humidityText.setText("" + mBarometerSensor.getHumidity());
                    temperatureText.setText("" + mTemperatureSensor.getTemperature());


            }
        });
    }
}