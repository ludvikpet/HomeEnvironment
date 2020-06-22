package com.example.homeenvironment;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.text.Layout;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_RECORD_AUDIO = 1;

    private static final int MIN_SWIPE_DIST = 20;
    private static final int MIN_SWIPE_SPEED = 200;
    private float posX;
    private float posY;
    private static int lastAction;
    Animation homeAnim;
    GestureDetector mGestureDetector;
    ViewGroup layout;
    Context mContext = this;
    private static final int REQUEST = 112;
    MotionEvent clickScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        layout = (ViewGroup) findViewById(R.id.home_activity);
        homeAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.home_anim);

        final Intent i = new Intent(this, MainActivity.class);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST);
        } else {
            layout.startAnimation(homeAnim);
        }

        homeAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(i);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("TAG", "@@@ PERMISSIONS grant");
                layout.startAnimation(homeAnim);
            } else {
                Log.d("TAG", "@@@ PERMISSIONS Denied");
                Toast.makeText(mContext, "PERMISSIONS Denied", Toast.LENGTH_LONG).show();
            }
        }
    }


}