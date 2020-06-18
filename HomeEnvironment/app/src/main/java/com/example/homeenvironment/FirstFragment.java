package com.example.homeenvironment;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
    private SharedPreferences sharedPreferences;

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

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mLightSensor = new AppLightSensor(view);
        mBarometerSensor = new AppBarometerSensor(view);
        mTemperatureSensor = new AppTemperatureSensor(view);
        // TODO Remove/move alarmCreateActivity
        final AlarmCreateActivity alarmCreateActivity = new AlarmCreateActivity(view);
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
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                luxText.setText(getString(R.string.lightLevelInfo, mLightSensor.getLux()));
                pressureText.setText(getString(R.string.pressureInfo, mBarometerSensor.getPressure()));
                humidityText.setText(getString(R.string.humidityInfo, mBarometerSensor.getHumidity()));
                if(sharedPreferences.getBoolean("temperature", false)) {
                    mTemperatureSensor.fahrenheit = true;
                    temperatureText.setText(getString(R.string.tempInfo, mTemperatureSensor.getTemperature(), "F"));
                }
                else {
                    mTemperatureSensor.fahrenheit = false;
                    temperatureText.setText(getString(R.string.tempInfo, mTemperatureSensor.getTemperature(), "℃"));
                }
                if (noiseLevel.soundDb(10 * Math.exp(-3)) < 0) {
                    noiseLevelText.setText(getString(R.string.noiseInfo, 0.0));
                } else {
                    noiseLevelText.setText(getString(R.string.noiseInfo, noiseLevel.getNoiseLevel()));

                }
                Log.i("Alarm","FirstFragment her! Setting Repeating" );
                alarmCreateActivity.setRepeating();
            }
        });
    }

    /**
     * Sets initial information for the text for the sensors
     */
    private void setInfo() {
        luxText.setText(getString(R.string.lightLevelInfo, 0));
        pressureText.setText(getString(R.string.pressureInfo, 0));
        humidityText.setText(getString(R.string.humidityInfo, 0));
        if(sharedPreferences.getBoolean("temperature", false)) {
            temperatureText.setText(getString(R.string.tempInfo, 0.0, "F"));
        } else {
            temperatureText.setText(getString(R.string.tempInfo, 0.0, "℃"));
        }
        noiseLevelText.setText(getString(R.string.noiseInfo, 0.0));
    }

    /**
     * Calculates and sets the scale for the view
     * @param view the view that needs scaling
     */

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

    /**
     * Method to set the scaling on the TextViews
     * @param luxTitle the title for the light level
     * @param pressureTitle the title for the pressure
     * @param humidityTitle the title for humidity
     * @param temperatureTitle the title for the temperature
     * @param noiseLevelTitle title for noise level
     * @param textSize the calculated text size for the TextViews
     * @param width the calculate width for the TextViews
     */
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

    /**
     * Method to streamline setting the text size and width of the TextViews
     * @param textView which TextView that needs to get set
     * @param textSize calculated text size
     * @param width calculated width
     */
    private void setDimensions(TextView textView, float textSize, int width){
        textView.setTextSize(textSize);
        textView.setWidth(width);
    }

}
