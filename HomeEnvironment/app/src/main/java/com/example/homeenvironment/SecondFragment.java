package com.example.homeenvironment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< Updated upstream
public class SecondFragment extends Fragment {
=======
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.Random;

public class SecondFragment extends Activity {
>>>>>>> Stashed changes

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_second);
        findViewById(R.id.button_temperature_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< Updated upstream
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("tipType", "temp");
                startActivity(popUp);
=======
                Intent popUp = new Intent(SecondFragment.this, TipTextScreen.class);
                popUp.putExtra("tipType", "tempTip");
                initializePopUp(popUp);
>>>>>>> Stashed changes
            }
        });
        findViewById(R.id.button_pressure_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< Updated upstream
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("tipType", "pressure");
                startActivity(popUp);
=======
                Intent popUp = new Intent(SecondFragment.this, TipTextScreen.class);
                popUp.putExtra("tipType", "pressureTip");
                initializePopUp(popUp);
>>>>>>> Stashed changes
            }
        });
        findViewById(R.id.button_light_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< Updated upstream
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("tipType", "light");
                startActivity(popUp);
=======
                Intent popUp = new Intent(SecondFragment.this, TipTextScreen.class);
                popUp.putExtra("tipType", "lightTip");
                initializePopUp(popUp);
>>>>>>> Stashed changes
            }
        });
        findViewById(R.id.button_humidity_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< Updated upstream
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("tipType", "humidity");
                startActivity(popUp);
=======
                Intent popUp = new Intent(SecondFragment.this, TipTextScreen.class);
                popUp.putExtra("tipType", "humidityTip");
                initializePopUp(popUp);
>>>>>>> Stashed changes
            }
        });
        findViewById(R.id.button_noise_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< Updated upstream
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("tipType", "noise");
                startActivity(popUp);
=======
                Intent popUp = new Intent(SecondFragment.this, TipTextScreen.class);
                popUp.putExtra("tipType", "noiseTip");
                initializePopUp(popUp);
>>>>>>> Stashed changes
            }
        });


    }


}