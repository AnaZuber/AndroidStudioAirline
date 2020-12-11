package com.example.airline.ui.flights;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.airline.R;

public class FlightFragment extends Fragment {

    private FlightViewModel flightViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        flightViewModel =
                new ViewModelProvider(this).get(FlightViewModel.class);
        View root = inflater.inflate(R.layout.fragment_flight, container, false);
        return root;
    }
}