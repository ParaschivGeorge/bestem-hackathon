package com.paraciuman.hackathon.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import org.json.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class WeatherApiController {

    @GetMapping(path = "/weather/{location}")
    String getWeather(@PathVariable String location){
        String response = "asas";

        return location;
    }

    public static void main(String[] args) {

    }
}
