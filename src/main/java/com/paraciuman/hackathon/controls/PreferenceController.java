package com.paraciuman.hackathon.controls;

import com.paraciuman.hackathon.model.Agenda;
import com.paraciuman.hackathon.model.Preference;
import com.paraciuman.hackathon.repository.AgendaRepository;
import com.paraciuman.hackathon.repository.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/preference")
public class PreferenceController {
    @Autowired
    private PreferenceRepository preferenceRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/preference/add")
    public void addPreference(@RequestBody final Preference preference, @RequestBody final Agenda agenda){
        if(preferenceRepository.findByAgenda(agenda) != null){
            return;
        }
        preferenceRepository.save(preference);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/preference/update")
    public void updatePreference(@RequestBody final Preference preference){
        if(preferenceRepository.findById(preference.getId()) == null)
            return;
        preferenceRepository.save(preference);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/preference/delete/{id}")
    public void deletePreference(@PathVariable("id") long id){
        if(preferenceRepository.findById(id) == null)
            return;
        preferenceRepository.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/preference/get/{id}")
    public Preference getPreference(@PathVariable("id") long id){
        Preference preference = preferenceRepository.findById(id);
        if(preference == null)
            return preference;
        else
            return null;
    }
}
