package com.paraciuman.hackathon.controls;



import com.paraciuman.hackathon.model.Agenda;
import com.paraciuman.hackathon.model.Loc;
import com.paraciuman.hackathon.model.Place;
import com.paraciuman.hackathon.model.Preference;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.paraciuman.hackathon.controls.HttpActions.getHTML;
import static com.paraciuman.hackathon.controls.HttpActions.getLatLong;


public class GooglePlacesControler {
    public static Set<Place> preferedPlaces(String loc, Agenda ag) throws Exception {

        JSONArray arr = places("Berlin").getJSONArray("results");
        JSONArray newArr = new JSONArray();
        Set<Place> prefPlaces = new HashSet<>();
        Agenda agenda = new AgendaController().getAgenda(ag.getId());
        Set<Preference> preferences = agenda.getPreferences();
        Place place = new Place();
        place.setAgenda(ag);
        for(int i = 0; i < arr.length(); i++){
            if(preferences.contains(arr.getJSONObject(i))){
                place.setName(arr.getJSONObject(i).getJSONObject("name").toString());
                place.setPhotoUrl("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + arr.getJSONObject(i).getJSONArray("photos").getJSONObject(0).getJSONObject("photos_reference"));
                prefPlaces.add(place);
            }
        }
        return prefPlaces;
    }


    public static JSONObject places(String loca) throws Exception {
        JSONObject json = new JSONObject();
        String location = "Berlin";
        Loc loc = getLatLong(location);
        json = getHTML("https://maps.googleapis.com/maps/api/place/nearbysearch/json ?location=" + loc.getLat()
                + loc.getLng() + "&key=AIzaSyBcxpOqVpsm6K7XWRSL0tCWfgQE29OlZlY");
        return json;
    }

}
