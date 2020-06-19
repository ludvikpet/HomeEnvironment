package com.example.homeenvironment;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

import static android.content.Context.SENSOR_SERVICE;
import static android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE;

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
    private WeatherRetriever weatherRetriever;
<<<<<<< Updated upstream
=======
    private boolean permissionMic, permissionLocation;
    private View view;
>>>>>>> Stashed changes

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.view = view;
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
        weatherRetriever = new WeatherRetriever(view);
        weatherRetriever.setWeather(view);
        weatherRetriever.getHumidity();
        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_DENIED) {
            noiseLevel = new NoiseLevel(view);
            if(!noiseLevel.isRunning()) noiseLevel.startRecorder();
        }

        setInfo();

        view.findViewById(R.id.tipsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    noiseLevel.stopRecorder();
<<<<<<< Updated upstream
                } catch (RuntimeException stopException) {
=======

                }catch (RuntimeException e){
>>>>>>> Stashed changes

                }
                Intent i = new Intent(getContext(), SecondFragment.class);
                startActivity(i);
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
                humidityText.setText(getString(R.string.humidityInfo, getHumidity()));
                if(AppTemperatureSensor.fahrenheit) {
                    temperatureText.setText(getString(R.string.tempInfo, getTemperature(), "F"));
                }
                else {
                    temperatureText.setText(getString(R.string.tempInfo, getTemperature(), "℃"));
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
<<<<<<< Updated upstream
=======
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.e("arbitrary", "It's saved");
        savedInstanceState.putString("tempText",  temperatureText.getText().toString());
        savedInstanceState.putString("luxText",  luxText.getText().toString());
        savedInstanceState.putString("pressureText",  pressureText.getText().toString());
        savedInstanceState.putString("humidityText",  humidityText.getText().toString());
        savedInstanceState.putString("noiseText",  noiseLevelText.getText().toString());
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.e("arbitrary", "Well, this runs. Something's fishy.");
        if(savedInstanceState != null){
            luxText.setText(savedInstanceState.getString("luxText"));
            pressureText.setText(savedInstanceState.getString("pressureText"));
            humidityText.setText(savedInstanceState.getString("humidityText"));
            temperatureText.setText(savedInstanceState.getString("tempText"));
            noiseLevelText.setText(savedInstanceState.getString("noiseText"));
            noiseLevel = new NoiseLevel(this.view);
            noiseLevel.startRecorder();
        }
    }

    private void initializePopUp(Intent popUp) {
        popUp.putExtra("info/tip", "info");
        startActivity(popUp);
    }

>>>>>>> Stashed changes
    private Float getTemperature(){

        Float temp = (!(mTemperatureSensor.hasTemperatureSensor())) ? Float.parseFloat(weatherRetriever.getTemperature()) : mTemperatureSensor.getTemperature();
        temp = (mTemperatureSensor.fahrenheit) ?  (temp * 9/5 + 32) : (temp-32) * 5/9;
        return temp;
    }
    private int getHumidity(){
        int humidity = (mBarometerSensor.hasHumiditySensor()) ? mBarometerSensor.getHumidity() : Integer.parseInt(weatherRetriever.getHumidity());
        return humidity;
    }
    private void setInfo() {
        luxText.setText(getString(R.string.lightLevelInfo, 0));
        pressureText.setText(getString(R.string.pressureInfo, 0));
        humidityText.setText(getString(R.string.humidityInfo, 0));
        if(mTemperatureSensor.fahrenheit == true){
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
