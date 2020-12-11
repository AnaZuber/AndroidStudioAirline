package com.example.airline.ui.location;

import androidx.lifecycle.ViewModel;


public class LocationViewModel extends ViewModel {

    private String address;
    private String city;
    private String postalCode;
    private String Country;


    public LocationViewModel() {

    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;

    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
