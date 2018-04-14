package com.paraciuman.hackathon.controls;

import com.paraciuman.hackathon.business.AgendaDiff;
import com.paraciuman.hackathon.model.Agenda;
import com.paraciuman.hackathon.model.Day;
import com.paraciuman.hackathon.model.Preference;
import com.paraciuman.hackathon.repository.AgendaRepository;
import com.paraciuman.hackathon.repository.UserRepository;
import com.paraciuman.hackathon.responseTypes.AgendaControllerResponse;
import com.paraciuman.hackathon.responseTypes.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.paraciuman.hackathon.business.AgendaDiff.agendaDiff;
import static com.paraciuman.hackathon.business.DayPlanner.planDays;
import static com.paraciuman.hackathon.controls.GooglePlacesControler.preferedPlaces;
import static com.paraciuman.hackathon.controls.WeatherApiController.getWeather;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/agenda/add/{email}")
    public void addAgenda(@PathVariable("email") String email){

    }

    @RequestMapping(method = RequestMethod.POST, value = "/agenda/update")
    public void updateAgenda(@RequestBody final Agenda agenda){
        if(agendaRepository.findById(agenda.getId()) == null)
            return;
        agendaRepository.save(agenda);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/agenda/delete/{id}")
    public void deleteAgenda(@PathVariable("id") long id){
        if(agendaRepository.findById(id) == null){
            return;
        }
        agendaRepository.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/agenda/get/{id}")
    public Agenda getAgenda(@PathVariable("id") long id){
        Agenda agenda = agendaRepository.findById(id);
        if(agenda == null){
            return null;
        }
        return agenda;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/agenda/up/{email}")
    public AgendaControllerResponse agendaUp(@RequestBody final Agenda agenda, @PathVariable("email") String email) throws Exception {
        Agenda agendaDB = agendaRepository.findById(agenda.getId());
        AgendaDiff.DiffType diffType;
        int i = 0;
        List<WeatherApiResponse> weatherApiResponses = new ArrayList<>();
        if(agendaDB != null){
            diffType = agendaDiff(agenda,agendaDB);
            switch (diffType){
                case MODIFIED:
                    agenda.setDay(planDays(createDays(agenda.getStartDate(),agenda.getEndDate(), agenda.getDays())));
                    break;
                case CALL_PLACES:

                    agenda.setPreferences(agendaDB.getPreferences());
                    agenda.setPlaces(preferedPlaces(agenda.getLocation(),agenda));

                    agendaRepository.save(agenda);
                    break;
                case CALL_WEATHER:
                    weatherApiResponses = getWeather(agenda.getLocation(),agenda.getStartDate(),agenda.getEndDate());
                    i = 0;
                    for(Day day : agenda.getDays()){
                        day.setWeather(weatherApiResponses.get(i++));
                    }
                    agendaRepository.save(agenda);
                    break;
                case CALL_BOTH:
                    weatherApiResponses = getWeather(agenda.getLocation(),agenda.getStartDate(),agenda.getEndDate());
                    i = 0;
                    for(Day day : agenda.getDays()){
                        day.setWeather(weatherApiResponses.get(i++));
                    }
                    agenda.setPreferences(agendaDB.getPreferences());
                    agenda.setPlaces(preferedPlaces(agenda.getLocation(),agenda));
                    agendaRepository.save(agenda);
            }
        }else{
            agenda.setUser(userRepository.findByEmail(email));
            agendaRepository.save(agenda);
        }

        return new AgendaControllerResponse();
    }


}
