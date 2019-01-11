package com.rabeech.geocodingapi.Contollers;

public class apiURL {

    String apiKey = "&key=AIzaSyDFBLh0dTAHy9fIethIAP2c9042R0xUjY4";
    String gmapsUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    String address;
    String completed;

    public apiURL(String address) {

        this.address = address;
        completed = gmapsUrl + this.address + apiKey;

    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getGmapsUrl() {
        return gmapsUrl;
    }

    public void setGmapsUrl(String gmapsUrl) {
        this.gmapsUrl = gmapsUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}