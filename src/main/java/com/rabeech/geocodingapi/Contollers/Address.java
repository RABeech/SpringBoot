package com.rabeech.geocodingapi.Contollers;

public class Address {

    private String userAddress;
    private String addressLatLong;

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getAddressLatLong() {
        return addressLatLong;
    }

    public void setAddressLatLong(String addressLatLong) {
        this.addressLatLong = addressLatLong;
    }
}
