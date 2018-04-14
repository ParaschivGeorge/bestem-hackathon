package com.paraciuman.hackathon.controls;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.paraciuman.hackathon.model.Loc;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpActions {

    public static JSONObject getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();

        JSONObject json = new JSONObject(result.toString());

        return json;
    }

    public static Loc getLatLong(String location) throws Exception{
        JSONObject latLong = getHTML("https://maps.googleapis.com/maps/api/geocode/json?address=" + location + "&key=AIzaSyBx8FdZBpJGNhOzNTECBsCTrwUyjnJAbnU").getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
        Loc loc = new Loc(latLong.getDouble("lat"), latLong.getDouble("lng"));
        return loc;
    }

}
