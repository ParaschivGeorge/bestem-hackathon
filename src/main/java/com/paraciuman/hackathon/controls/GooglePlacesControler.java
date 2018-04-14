package com.paraciuman.hackathon.controls;



import com.paraciuman.hackathon.model.Agenda;
import com.paraciuman.hackathon.model.Loc;
import com.paraciuman.hackathon.model.Preference;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static com.paraciuman.hackathon.controls.HttpActions.getHTML;
import static com.paraciuman.hackathon.controls.HttpActions.getLatLong;


public class GooglePlacesControler {
    public static JSONObject preferedPlaces(@RequestBody final long agenda_id) throws Exception {

        JSONArray arr = places("Berlin").getJSONArray("results");
        JSONArray newArr = new JSONArray();
        Agenda agenda = new AgendaController().getAgenda(agenda_id);
        Set<Preference> preferences = agenda.getPreferences();

        for(int i = 0; i < arr.length(); i++){
            if(preferences.contains(arr.getJSONObject(i))){
                newArr.put(arr.getJSONObject(i));
            }
        }
        return  new JSONObject().put("results",newArr);
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
