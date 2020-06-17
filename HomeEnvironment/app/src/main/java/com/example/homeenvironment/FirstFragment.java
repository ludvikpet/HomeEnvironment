package com.example.homeenvironment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.homeenvironment.Sensors.AppBarometerSensor;
import com.example.homeenvironment.Sensors.AppLightSensor;
import com.example.homeenvironment.Sensors.AppTemperatureSensor;
import com.example.homeenvironment.Sensors.NoiseLevel;

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
        final AlarmCreateActivity alarmCreateActivity = new AlarmCreateActivity(view);
        //luxText = view.findViewById(R.id.Lux_Measurement);
        //pressureText = view.findViewById(R.id.pressureSensorView);
        //humidityText = view.findViewById(R.id.Humidity_Text);
        //temperatureText = view.findViewById(R.id.Temperature_Text);
        luxText = view.findViewById(R.id.lightID);
        pressureText = view.findViewById(R.id.pressureID);
        humidityText = view.findViewById(R.id.humidityID);
        temperatureText = view.findViewById(R.id.temperatureID);
        noiseLevelText = view.findViewById(R.id.noiseID);

        setScale(view);

        if (ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_DENIED) {
            noiseLevel = new NoiseLevel(view);
            if(!noiseLevel.isRunning()) noiseLevel.startRecorder();
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
                temperatureText.setText(getString(R.string.tempInfo, mTemperatureSensor.getTemperature(), "℃"));
                if (noiseLevel.soundDb(10 * Math.exp(-3)) < 0) {
                    noiseLevelText.setText(getString(R.string.noiseInfo, 0.0));
                } else {
                    noiseLevelText.setText(getString(R.string.noiseInfo, noiseLevel.getNoiseLevel()));
                    alarmCreateActivity.setRepeating();
                }
            }
        });
    }


    private void setInfo() {
        luxText.setText(getString(R.string.lightLevelInfo, 0));
        pressureText.setText(getString(R.string.pressureInfo, 0));
        humidityText.setText(getString(R.string.humidityInfo, 0));
        temperatureText.setText(getString(R.string.tempInfo, 0.0, "℃"));
        noiseLevelText.setText(getString(R.string.noiseInfo, 0.0));
    }

    private void setScale(View view){
        int width = getResources().getConfiguration().screenWidthDp;
        int height = getResources().getConfiguration().screenHeightDp;
        float heightProduction = 800;
        float widthProduction = 480;
        int calculatedWidth = (int) (width * (100/ widthProduction));
        Log.i("TAG", "Width: " +calculatedWidth);
        float textSize = height * (25 / heightProduction) ;
        Log.i("TAG", "TextSize " + height*(25/heightProduction));
        TextView luxTitle, pressureTitle, humidityTitle, temperatureTitle, noiseLevelTitle;

        luxTitle = view.findViewById(R.id.lightTextView);
        pressureTitle = view.findViewById(R.id.pressureTextView);
        humidityTitle = view.findViewById(R.id.humidityTextView);
        temperatureTitle = view.findViewById(R.id.tempTextView);
        noiseLevelTitle = view.findViewById(R.id.noiseTextView);

        Button measureButton = view.findViewById(R.id.measureButton);
        Button tipsButton = view.findViewById(R.id.tipsButton);
        measureButton.setTextSize(textSize);
        tipsButton.setTextSize(textSize);
        setTitles(luxTitle, pressureTitle, humidityTitle, temperatureTitle, noiseLevelTitle, textSize, calculatedWidth);
    }
    private void setTitles(TextView luxTitle, TextView pressureTitle, TextView humidityTitle, TextView temperatureTitle, TextView noiseLevelTitle, float textSize, int width){
        luxTitle.setTextSize(textSize);
        pressureTitle.setTextSize(textSize);
        humidityTitle.setTextSize(textSize);
        temperatureTitle.setTextSize(textSize);
        noiseLevelTitle.setTextSize(textSize);
        setDimensions(luxText, textSize, width);
        setDimensions(pressureText, textSize, width);
        setDimensions(humidityText, textSize, width);
        setDimensions(temperatureText, textSize, width);
        setDimensions(noiseLevelText, textSize, width);
    }
    private void setDimensions(TextView textView, float textSize, int width){
        textView.setTextSize(textSize);
        textView.setWidth(width);
    }

}
