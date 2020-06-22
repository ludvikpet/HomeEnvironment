package com.example.homeenvironment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import com.example.homeenvironment.tips.TipFragment;

public class SwipeListener implements View.OnTouchListener {
    private int MIN_SWIPE_DIST = 100;
    private float startX, startY, dX, dY;
    View view;
    private Constants constants = Constants.getInstance();

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        this.view = view;

        switch(event.getAction()) { // Check vertical and horizontal touches
            case MotionEvent.ACTION_DOWN: {
                startX = event.getX();
                startY = event.getY();
                return true;
            }
            case MotionEvent.ACTION_UP: {

                float dX = startX - event.getX();
                float dY = startY - event.getY();

                //HORIZONTAL SCROLL
                if (Math.abs(dX) > Math.abs(dY)) {
                    if (Math.abs(dX) > MIN_SWIPE_DIST) {
                        // left or right
                        if (dX > 0) {
                            rightSwipe();
                            return true;
                        }
                        if (dX < 0) {
                            leftSwipe();
                            return true;
                        }
                    }
                } else if(Math.abs(dX) < Math.abs(dY) && Math.abs(dY) > MIN_SWIPE_DIST) {
                    if(dY > 0) {
                        topSwipe();
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public void rightSwipe(){
        if(view.getId() == R.id.relativeLayout) {
            NavHostFragment.findNavController(constants.getFragment())
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);

        }
    }

    public void leftSwipe(){
        if(view.getId() == R.id.tips_layout) {
            NavHostFragment.findNavController(constants.getFragment())
                    .navigate(R.id.action_SecondFragment_to_FirstFragment);
        }
    }

    public void topSwipe() {
        if(view.getId() == R.id.relativeLayout) {
            Log.i("Swipe", "SWIPED RIGHT!");
            Intent i = new Intent(view.getContext(), Settings.class);
            view.getContext().startActivity(i);
        }
    }
}
