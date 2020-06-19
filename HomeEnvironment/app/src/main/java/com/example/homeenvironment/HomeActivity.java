package com.example.homeenvironment;

        import android.Manifest;
        import android.content.ClipData;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.gesture.GestureOverlayView;
        import android.gesture.Prediction;
        import android.os.Bundle;

        import com.google.android.material.floatingactionbutton.FloatingActionButton;
        import com.google.android.material.snackbar.Snackbar;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
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
        import android.view.animation.Animation;
        import android.view.animation.AnimationSet;
        import android.view.animation.AnimationUtils;
        import android.widget.Button;
        import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_RECORD_AUDIO = 1;

    private static final int MIN_SWIPE_DIST = 20;
    private static final int MIN_SWIPE_SPEED = 200;
    private float posX;
    private float posY;
    private static int lastAction;
    ImageView homebg;
    Animation homeAnim;
    GestureDetector mGestureDetector;

    MotionEvent clickScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        homebg = (ImageView) findViewById(R.id.homebg);
        homeAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.home_anim);

        homebg.startAnimation(homeAnim);

        final Intent i = new Intent(this, MainActivity.class);

        homeAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if(animation.equals(homeAnim)) {
                    Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.other_components_anim);
                    findViewById(R.id.leaves).startAnimation(anim1);

                    Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.other_components_anim);
                    findViewById(R.id.greenEllipse).startAnimation(anim1);

                    Animation anim3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.other_components_anim);
                    findViewById(R.id.whiteEllipse).startAnimation(anim1);

                    Animation anim4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.other_components_anim);
                    findViewById(R.id.lGreenEllipse).startAnimation(anim1);

                    Animation anim5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.other_components_anim);
                    findViewById(R.id.lWhiteEllipse).startAnimation(anim1);

                    Animation anim6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.other_components_anim);
                    findViewById(R.id.logo).startAnimation(anim1);
                }



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

}