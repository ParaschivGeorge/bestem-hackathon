package com.paraciuman.hackathon.controls;



import com.paraciuman.hackathon.model.Loc;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.paraciuman.hackathon.controls.HttpActions.getHTML;
import static com.paraciuman.hackathon.controls.HttpActions.getLatLong;

@RestController
public class GooglePlacesControler {

    @GetMapping(path = "/places")
    public static JSONObject places() throws Exception {
        JSONObject json = new JSONObject();
        String location = "Berlin";
        Loc loc = getLatLong(location);
        json = getHTML("https://maps.googleapis.com/maps/api/place/nearbysearch/json ?location=" + loc.getLat()
                + loc.getLng() + "&key=AIzaSyBcxpOqVpsm6K7XWRSL0tCWfgQE29OlZlY");
        return json;
    }

}
