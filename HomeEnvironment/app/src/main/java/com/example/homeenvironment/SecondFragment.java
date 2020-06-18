package com.example.homeenvironment;

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

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.Random;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.button_temperature_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("tipType", "tempTip");
                initializePopUp(popUp);
            }
        });
        view.findViewById(R.id.button_pressure_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("tipType", "pressureTip");
                initializePopUp(popUp);
            }
        });
        view.findViewById(R.id.button_light_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("tipType", "lightTip");
                initializePopUp(popUp);
            }
        });
        view.findViewById(R.id.button_humidity_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("tipType", "humidityTip");
                initializePopUp(popUp);
            }
        });
        view.findViewById(R.id.button_noise_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("tipType", "noiseTip");
                initializePopUp(popUp);
            }
        });


    }
        private void initializePopUp(Intent popUp) {
        popUp.putExtra("info/tip", "tip");
        startActivity(popUp);
        Random random = new Random();
        int potentialWindMill = random.nextInt(25);
        if(potentialWindMill < 1) {
            Animatoo.animateSpin(getContext());
        } else {
            Animatoo.animateFade(getContext());
        }
    }
}