package com.example.homeenvironment;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.homeenvironment.Alarm.AlarmCreateActivity;
import com.example.homeenvironment.Sensors.AppBarometerSensor;
import com.example.homeenvironment.Sensors.AppLightSensor;
import com.example.homeenvironment.Sensors.AppTemperatureSensor;
import com.example.homeenvironment.Sensors.NoiseLevel;
import com.example.homeenvironment.Sensors.WeatherRetriever;
import com.example.homeenvironment.tips.TipTextScreen;

public class MeasurementFragment extends Fragment {
    private AppLightSensor mLightSensor;
    private AppBarometerSensor mBarometerSensor;
    private AppTemperatureSensor mTemperatureSensor;
    private SensorEventListener lightEventListener;
    private float maxValue;
    private float lightQuantity;
    private TextView luxText, pressureText, humidityText, temperatureText, noiseLevelText;
    private NoiseLevel noiseLevel;
    private AlarmCreateActivity alarmCreateActivity;
    // private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferences;
    private WeatherRetriever weatherRetriever;
    private boolean permissionMic;
    private View mRootView;
    private float currentNoiseLevel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mRootView == null)
            mRootView = inflater.inflate(R.layout.fragment_measurement, container, false);
        return mRootView;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        permissionMic = ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_DENIED;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mLightSensor = new AppLightSensor(view);
        mBarometerSensor = new AppBarometerSensor(view);
        mTemperatureSensor = new AppTemperatureSensor(view);
        // TODO Remove/move alarmCreateActivity
        final AlarmCreateActivity alarmCreateActivity = new AlarmCreateActivity(view);
        alarmCreateActivity.resetAlarmNotification();
        luxText = view.findViewById(R.id.lightID);
        pressureText = view.findViewById(R.id.pressureID);
        humidityText = view.findViewById(R.id.humidityID);
        temperatureText = view.findViewById(R.id.temperatureID);
        noiseLevelText = view.findViewById(R.id.noiseID);
        setScale(view);
        weatherRetriever = new WeatherRetriever(view);
        weatherRetriever.setWeather(view);
        weatherRetriever.getHumidity();
        if (permissionMic) {
            noiseLevel = new NoiseLevel(view);
            currentNoiseLevel = noiseLevel.getNoiseLevel();
            Log.e("NOISE", " " + noiseLevel.isRunning());
        }

        view.findViewById(R.id.tipsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    noiseLevel.stopRecorder();
                } catch (RuntimeException stopException) {
                    Log.i("NOISE ", "stopRecording() failed");

                }
                NavHostFragment.findNavController(MeasurementFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);

            }
        });

        view.findViewById(R.id.measureButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                luxText.setText(getString(R.string.lightLevelInfo, mLightSensor.getLux()));
                pressureText.setText(getString(R.string.pressureInfo, mBarometerSensor.getPressure()));
                humidityText.setText(getString(R.string.humidityInfo, getHumidity()));

                //Set color values:
                setColor(luxText, mLightSensor.getLux());
                setColor(pressureText, mBarometerSensor.getPressure());
                setColor(humidityText, getHumidity());
                setColor(temperatureText, getTemperature().intValue());

                //Switch between temperature measuring unit:
                if (AppTemperatureSensor.temperatureMode.equals("true")) {
                    temperatureText.setText(getString(R.string.tempInfo, getTemperature(), "F"));
                } else {
                    temperatureText.setText(getString(R.string.tempInfo, getTemperature(), "℃"));

                }

                //Set correct noise level:
                currentNoiseLevel = noiseLevel.getNoiseLevel();
                if (noiseLevel != null && currentNoiseLevel < 0) {
                    Log.e("NOISE", " " + noiseLevel.getAmplitude());
                    noiseLevelText.setText(getString(R.string.noiseInfo, 0.0));

                } else if (noiseLevel == null){
                    noiseLevel = new NoiseLevel(view);

                    if(currentNoiseLevel > 0) {
                        noiseLevelText.setText(getString(R.string.noiseInfo, currentNoiseLevel));
                    }
                }else {
                    noiseLevelText.setText(getString(R.string.noiseInfo, currentNoiseLevel));
                }
                Log.e("NOISE", "Current Noise Level: " + currentNoiseLevel);

                setColor(noiseLevelText,  currentNoiseLevel);

                //Start alarm
                alarmCreateActivity.resetAlarmNotification();
            }
        });
        //Sæt onClickListeners på alle informationsknapperne på siden og sørg for at den
        //rigtige information bliver sendt afsted til tekstviewet
        view.findViewById(R.id.button_tempInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("infoType", "tempInfo");
                initializePopUp(popUp);
            }
        });

        view.findViewById(R.id.button_pressureInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("infoType", "pressureInfo");
                initializePopUp(popUp);
            }
        });

        view.findViewById(R.id.button_lightInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("infoType", "lightInfo");
                initializePopUp(popUp);
            }
        });

        view.findViewById(R.id.button_humidityInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("infoType", "humidityInfo");
                initializePopUp(popUp);
            }
        });

        view.findViewById(R.id.button_noiseInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("infoType", "noiseInfo");
                initializePopUp(popUp);
            }
        });

    }

    private void initializePopUp(Intent popUp) {
        popUp.putExtra("info/tip", "info");
        startActivity(popUp);
    }

    private Float getTemperature() {

        float temp = (!(mTemperatureSensor.hasTemperatureSensor())) ? Float.parseFloat(weatherRetriever.getTemperature()) : mTemperatureSensor.getTemperature();
        if (mTemperatureSensor.temperatureMode.equals("true")) {
            return (temp * 9 / 5 + 32);
        } else return temp;

    }

    private int getHumidity() {
        int humidity = (!(mBarometerSensor.hasHumiditySensor())) ? Integer.parseInt(weatherRetriever.getHumidity()) : mBarometerSensor.getHumidity();
        return humidity;
    }

    private void setInfo() {
        luxText.setText(getString(R.string.lightLevelInfo, 0));
        pressureText.setText(getString(R.string.pressureInfo, 0));
        humidityText.setText(getString(R.string.humidityInfo, 0));
        if (mTemperatureSensor.temperatureMode.equals("true")) {
            temperatureText.setText(getString(R.string.tempInfo, 0.0, "F"));
        } else {
            temperatureText.setText(getString(R.string.tempInfo, 0.0, "℃"));
        }
        noiseLevelText.setText(getString(R.string.noiseInfo, 0.0));
    }

    /**
     * Calculates and sets the scale for the view
     *
     * @param view the view that needs scaling
     */

    private void setScale(View view) {
        int width = getResources().getConfiguration().screenWidthDp;
        int height = getResources().getConfiguration().screenHeightDp;
        float heightProduction = 800;
        float widthProduction = 480;
        int calculatedWidth = (int) (width * (100 / widthProduction));
        Log.i("TAG", "Width: " + calculatedWidth);
        float textSize = height * (25 / heightProduction);
        Log.i("TAG", "TextSize " + height * (25 / heightProduction));
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
     *
     * @param luxTitle         the title for the light level
     * @param pressureTitle    the title for the pressure
     * @param humidityTitle    the title for humidity
     * @param temperatureTitle the title for the temperature
     * @param noiseLevelTitle  title for noise level
     * @param textSize         the calculated text size for the TextViews
     * @param width            the calculate width for the TextViews
     */
    private void setTitles(TextView luxTitle, TextView pressureTitle, TextView humidityTitle, TextView temperatureTitle, TextView noiseLevelTitle, float textSize, int width) {
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
     *
     * @param textView which TextView that needs to get set
     * @param textSize calculated text size
     * @param width    calculated width
     */
    private void setDimensions(TextView textView, float textSize, int width) {
        textView.setTextSize(textSize);
        textView.setWidth(width);
    }
    //Hvis en klimaparameter er enten for høj eller lav, fav den korresponderende farve.
    private void setColor(TextView textView, double value) {

        //Temperature color:
        if(textView.equals(temperatureText)) {

            if(mTemperatureSensor.temperatureMode.equals("true")) {

                if(value >= 68 && value <= 74.3) {
                    temperatureText.setTextColor(getResources().getColor(R.color.green_measure));

                } else if(value >= 68 && value <= 74.3) {
                    temperatureText.setTextColor(getResources().getColor(R.color.yellow_measure));

                } else {
                    temperatureText.setTextColor(getResources().getColor(R.color.red_measure));

                }

            } else {

                if(value >= 21.5 && value <= 22.5) {
                    temperatureText.setTextColor(getResources().getColor(R.color.green_measure));

                } else if(value >= 20 && value <= 23.5) {
                    temperatureText.setTextColor(getResources().getColor(R.color.yellow_measure));

                } else {
                    temperatureText.setTextColor(getResources().getColor(R.color.red_measure));

                }

            }

        }

        //Pressure color:
        else if(textView.equals(pressureText)) {
            //mBarometerSensor.getPressure()
            if(value >= 950  && value <= 1050) {
                textView.setTextColor(getResources().getColor(R.color.green_measure));

            } else if(value >= 900 && value <= 1100) {
                textView.setTextColor(getResources().getColor(R.color.yellow_measure));

            } else {
                textView.setTextColor(getResources().getColor(R.color.red_measure));

            }
        }

        else if(textView.equals(luxText)) {

            if(value >= 300  && value <= 500) {
                textView.setTextColor(getResources().getColor(R.color.green_measure));

            } else if(value >= 250 && value <= 550) {
                textView.setTextColor(getResources().getColor(R.color.yellow_measure));

            } else {
                textView.setTextColor(getResources().getColor(R.color.red_measure));

            }
        }

        else if(textView.equals(humidityText)) {

            if(value >= 45  && value <= 55) {
                textView.setTextColor(getResources().getColor(R.color.green_measure));

            } else if(value >= 20 && value <= 70) {
                textView.setTextColor(getResources().getColor(R.color.yellow_measure));

            } else {
                textView.setTextColor(getResources().getColor(R.color.red_measure));

            }

        }

        else if(textView.equals(noiseLevelText)) {

            if(value < 60.0) {
                textView.setTextColor(getResources().getColor(R.color.green_measure));

            } else if(value >= 60.0  && value <= 65.0) {
                textView.setTextColor(getResources().getColor(R.color.yellow_measure));

            } else {
                textView.setTextColor(getResources().getColor(R.color.red_measure));

            }

        }


    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Noise ", "onPause() in fragment");
        if (noiseLevel != null) {
            try {
                noiseLevel.stopRecorder();
            } catch (RuntimeException stopException) {

            }
        }
    }

    /*
    @Override
    public void onStart() {
        super.onStart();
        alarmCreateActivity = new AlarmCreateActivity(mRootView);

    }
    */

}
