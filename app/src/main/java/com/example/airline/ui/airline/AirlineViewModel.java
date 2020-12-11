package com.example.airline.ui.airline;

import androidx.lifecycle.ViewModel;


public class AirlineViewModel extends ViewModel {

    private String name;


    public AirlineViewModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
