package com.paraciuman.hackathon.controls;

import com.paraciuman.hackathon.model.Agenda;
import com.paraciuman.hackathon.model.Place;
import com.paraciuman.hackathon.model.Preference;
import com.paraciuman.hackathon.repository.PlacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlacesRepository placesRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/place/add")
    public void addPreference(@RequestBody final Place place, @RequestBody final Agenda agenda){
        if(placesRepository.findByAgenda(agenda) != null){
            return;
        }
        placesRepository.save(place);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/place/update")
    public void updatePreference(@RequestBody final Place place){
        if(placesRepository.findById(place.getId()) == null)
            return;
        placesRepository.save(place);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/place/delete/{id}")
    public void deletePreference(@PathVariable("id") long id){
        if(placesRepository.findById(id) == null)
            return;
        placesRepository.deleteById(id);
    }

    public Place place(@PathVariable("id") long id){
        Place place= placesRepository.findById(id);
        if(place == null)
            return place;
        else
            return null;
    }
}
