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
                popUp.putExtra("info/tip", "tip");
                popUp.putExtra("tipType", "tempTip");
                startActivity(popUp);
            }
        });
        view.findViewById(R.id.button_pressure_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("info/tip", "tip");
                popUp.putExtra("tipType", "pressureTip");
                startActivity(popUp);
            }
        });
        view.findViewById(R.id.button_light_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("info/tip", "tip");
                popUp.putExtra("tipType", "lightTip");
                startActivity(popUp);
            }
        });
        view.findViewById(R.id.button_humidity_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("info/tip", "tip");
                popUp.putExtra("tipType", "humidityTip");
                startActivity(popUp);
            }
        });
        view.findViewById(R.id.button_noise_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popUp = new Intent(getActivity(), TipTextScreen.class);
                popUp.putExtra("info/tip", "tip");
                popUp.putExtra("tipType", "noiseTip");
                startActivity(popUp);
            }
        });


    }


}