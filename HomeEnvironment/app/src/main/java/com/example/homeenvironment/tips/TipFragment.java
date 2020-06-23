package com.example.homeenvironment.tips;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.homeenvironment.Constants;
import com.example.homeenvironment.R;
import com.example.homeenvironment.SwipeListener;

//Denne klasse står for den side af vores app som indeholder en masse knapper man kan klikke på, for at få nogle tips.
public class TipFragment extends Fragment {
    private Constants constants = Constants.getInstance();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tips, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        constants.setFragment(this);
//Her sættes der onClickListeners på alle knapperne på skærmen. Desuden sendes der en extra som
// fortæller hvilken knap der blev klikket på når en tip dialog skal vises.
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

        view.setOnTouchListener(new SwipeListener());

    }
        private void initializePopUp(Intent popUp) {
        popUp.putExtra("info/tip", "tip");
        startActivity(popUp);
        Animatoo.animateFade(getContext());
    }
}