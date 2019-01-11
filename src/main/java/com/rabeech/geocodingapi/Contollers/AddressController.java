/*
Adam Beech
1/11/2018

Spring Boot application that returns the latitude and longitude for a given
street address using a Google Maps API
 */
package com.rabeech.geocodingapi.Contollers;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class AddressController {

    /*
    GET method for creating a view that holds a form for users to enter
    a street address. The results from the user entered info is stored
    in an Address object.
     */
    @GetMapping("/getLatLong")
    public String addressForm(Model model) {
        model.addAttribute("userAddy", new Address());
        return "address";
    }

    /*
    POST method to return a view after a form submission. Form creates an
    Address object and stores a user entered value in 'userAddress' object
    property. 'userAddress' is stored as a String. This method is cached
    to avoid redundant subsequent invocations.
     */
    @PostMapping("/getLatLong")
    @Cacheable("Address")
    public String addressSubmit(@ModelAttribute Address userAddy) {

            try {
                accessAPI(userAddy);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "result";

    }

    /*
    Method creates a unique API GET url using an apiURL object. It receives an
    Address object passed by the addressSubmit POST method. The method extracts
    the address string and formats it to the correct structure for the API url.
    Connection is established to send the API request and a JSON object is used
    to read the response from the API and store the results. The store results
    is placed into an Address object in the 'addressLatLong' property   .
     */
    private void accessAPI(Address userAddy) throws Exception {

        String startingAPI = userAddy.getUserAddress();
        String apiAddress = startingAPI.replaceAll(" ", "+");
        apiURL url = new apiURL(apiAddress);

        URL obj = new URL(url.completed);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Chrome");

        //int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'GET' request to URL : " + url.completed);
        //System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        JSONObject apiResponse = new JSONObject(response.toString());
        String returnApiLatLong = apiResponse.getString("location");

        userAddy.setAddressLatLong(returnApiLatLong);

    }

}