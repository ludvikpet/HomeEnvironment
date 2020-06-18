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

public class NoiseLevel extends AppCompatActivity {


    TextView mStatusView;
    MediaRecorder mRecorder;
    Thread runner;
    private static double mEMA = 0.0;
    static final private double EMA_FILTER = 0.6;
    private final static String TAG = "Noise Level ";
    private final Handler mHandler = new Handler();
    private boolean permission;
    private View view;
    private boolean isRunning;


    final Runnable updater = new Runnable() {
        public void run() {
            updateTv();
        }
    };


    //    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
    public NoiseLevel(View view) {
        this.view = view;
        Log.i(TAG, "CREATION");
//        setContentView(R.layout.fragment_first);
//        mStatusView = findViewById(R.id.noiseID);
//        mStatusView.setText("0.00 dB");
        permission = ContextCompat.checkSelfPermission(this.view.getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_DENIED;
        if (runner == null && permission) {
            runner = new Thread() {
                public void run() {
                    while (runner != null) {
                        try {
                            Thread.sleep(600);
//                            Log.i(TAG, "Current noise: " + (float) soundDb(10 * Math.exp(-3)));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.post(updater);
                    }
                }
            };
            runner.start();
            Log.d(TAG, "start runner()");
        }
    }

    public void onResume() {

        super.onResume();
        isRunning = true;
        startRecorder();
    }

    public void onPause() {
        super.onPause();
        isRunning = false;
        stopRecorder();
    }

    public void startRecorder() {
        if (mRecorder == null && permission) {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.HE_AAC);
            mRecorder.setOutputFile("/dev/null");
            isRunning = true;
            try {
                mRecorder.prepare();
            } catch (java.io.IOException ioe) {
                Log.i("IO ", "IOException: " + android.util.Log.getStackTraceString(ioe));

            } catch (java.lang.SecurityException e) {
                Log.i("Security ", "SecurityException: " + android.util.Log.getStackTraceString(e));
            }
            try {
                mRecorder.start();
            } catch (java.lang.SecurityException e) {
                Log.i("Security ", "SecurityException: " + android.util.Log.getStackTraceString(e));
            }

        }

    }

    public void stopRecorder() {
        if (mRecorder != null) {
            isRunning = false;
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

    public void updateTv() {
//        mStatusView.setText(getString(R.string.noiseInfo, (float) soundDb(10 * Math.exp(-3))));
    }

    public double soundDb(double ampl) {
        return 20 * Math.log10(getAmplitudeEMA() / ampl);
    }

    public double getAmplitude() {
        if (mRecorder != null)
            return (mRecorder.getMaxAmplitude());
        else
            return 0;

    }


    public double getAmplitudeEMA() {
        double amp = getAmplitude();
        mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
        return mEMA;
    }

    public float getNoiseLevel() {
        return (float) soundDb(10 * Math.exp(-3));
    }

    public void stopThread() {
        runner.interrupt();
    }
    public boolean isRunning(){
        return isRunning;
    }
}