package com.example.homeenvironment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.homeenvironment.Sensors.AppBarometerSensor;
import com.example.homeenvironment.Sensors.AppLightSensor;
import com.example.homeenvironment.Sensors.AppTemperatureSensor;

public class FirstFragment extends Fragment {
    private static final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    private AppLightSensor mLightSensor;
    private AppBarometerSensor mBarometerSensor;
    private AppTemperatureSensor mTemperatureSensor;
    private SensorEventListener lightEventListener;
    private float maxValue;
    private float lightQuantity;
    private TextView luxText, pressureText, humidityText, temperatureText, noiseLevelText;
    private NoiseLevel noiseLevel;
    private WeatherRetriever weatherRetriever;

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
        noiseLevelText = view.findViewById(R.id.noiseID);
        weatherRetriever = new WeatherRetriever(view);
        weatherRetriever.setWeather(view);
        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_DENIED) {
            noiseLevel = new NoiseLevel(view);
           // noiseLevel.startRecorder();
        }
        setInfo();


        view.findViewById(R.id.tipsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    noiseLevel.stopRecorder();
                } catch (RuntimeException stopException) {

                }
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

//        view.findViewById(R.id.button_noiselevel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                    NavHostFragment.findNavController(FirstFragment.this)
//                            .navigate(R.id.action_FirstFragment_to_Noiselevel);

        view.findViewById(R.id.measureButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luxText.setText(getString(R.string.lightLevelInfo, mLightSensor.getLux()));
                pressureText.setText(getString(R.string.pressureInfo, mBarometerSensor.getPressure()));
                humidityText.setText(getString(R.string.humidityInfo, mBarometerSensor.getHumidity()));
                if(AppTemperatureSensor.fahrenheit) {
                    temperatureText.setText(getString(R.string.tempInfo, mTemperatureSensor.getTemperature(), "F"));
                }
                else {
                    temperatureText.setText(getString(R.string.tempInfo, mTemperatureSensor.getTemperature(), "℃"));
                }
                if (noiseLevel.soundDb(10 * Math.exp(-3)) < 0) {
                    noiseLevelText.setText(getString(R.string.noiseInfo, 0.0));
                } else {
                    noiseLevelText.setText(getString(R.string.noiseInfo, noiseLevel.getNoiseLevel()));
                }
                alarmCreateActivity.setRepeating();
            }
        });
    }

    private void setInfo() {
        luxText.setText(getString(R.string.lightLevelInfo, 0));
        pressureText.setText(getString(R.string.pressureInfo, 0));
        humidityText.setText(getString(R.string.humidityInfo, 0));
        if(AppTemperatureSensor.fahrenheit == true){
            temperatureText.setText(getString(R.string.tempInfo, 0.0, "F"));
        }
        else{
            temperatureText.setText(getString(R.string.tempInfo, 0.0, "℃"));
        }
        noiseLevelText.setText(getString(R.string.noiseInfo, 0.0));
    }

}