package com.example.homeenvironment.Sensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.homeenvironment.R;

import java.io.IOException;

public class NoiseLevel extends AppCompatActivity {


    TextView mStatusView;
    MediaRecorder mRecorder;
    private static double mEMA = 0.0;
    static final private double EMA_FILTER = 0.6;
    private final static String TAG_noise = "Noise Level ";
    private static String fileName = "/dev/null";
    private boolean isRunning;

    public double soundDb(double ampl) {
        return 20 * Math.log10(getAmplitudeEMA() / ampl);
    }

    /**
     * Get current highest amplitude
     * @return returns maximum absolute measured amplitude
     */
    public double getAmplitude() {
        if (mRecorder != null)
            return (mRecorder.getMaxAmplitude());
        else
            return 0;

    }

    /**
     * Gets average amplitude with specified filters
     * @return returns double average amplitude
     */
    public double getAmplitudeEMA() {
        double amp = getAmplitude();
        mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
        return mEMA;
    }

    public float getNoiseLevel() {
        return (float) soundDb(10 * Math.exp(-3));
    }

    public void onRecord(boolean start) {
        if (start) {
            startRecorder();
        }
    }

    /**
     * Starts the recorder and checks that there isn't already a mRecorder
     */
    public void startRecorder() {
        if(mRecorder == null){
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(fileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(TAG_noise, "prepare() failed");
        }
        isRunning = true;
        mRecorder.start();
        }
    }

    /**
     * Stops the recorder
     */
    public void stopRecorder() {
        Log.i(TAG_noise, "mRecorder stopping with stopRecorder()");
        isRunning = false;
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mRecorder != null) {
            Log.i(TAG_noise, "onStop()");
            mRecorder.release();
            mRecorder = null;
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG_noise, "onPause()");
        stopRecorder();
    }

    @Override
    public void onResume() {
        super.onResume();
        startRecorder();
    }

    /**
     * Constructor for NoiseLevel
     * @param view for current view
     */
    public NoiseLevel(View view) {
        onRecord(!isRunning);
    }

    public boolean isRunning(){
        return isRunning;
    }
}