package com.paraciuman.hackathon.controls;

import com.paraciuman.hackathon.business.AgendaDiff;
import com.paraciuman.hackathon.model.*;
import com.paraciuman.hackathon.repository.AgendaRepository;
import com.paraciuman.hackathon.repository.UserRepository;
import com.paraciuman.hackathon.requestBodyTypes.AgendaEmailRequestBody;
import com.paraciuman.hackathon.responseTypes.AgendaControllerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static com.paraciuman.hackathon.business.AgendaDiff.agendaDiff;
import static com.paraciuman.hackathon.business.DayPlanner.planDays;
import static com.paraciuman.hackathon.business.DaysCreator.createDays;
import static com.paraciuman.hackathon.controls.GooglePlacesControler.preferedPlaces;
import static com.paraciuman.hackathon.controls.WeatherApiController.getWeather;

@RestController
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

    @GetMapping(path = "/agenda/get")
    public AgendaEmailRequestBody aerb(){
        AgendaEmailRequestBody ar = new AgendaEmailRequestBody();
        HashSet<Day> hs = new HashSet<Day>();
        Day nd = new Day();
        Place place = new Place();
        place.setAgenda(ar.getAgenda());
        place.setEndTime(new Time(2,3,4));
        place.setEstimation(new Time(2));
        place.setName("hackaton");
        place.setPhotoUrl("aha");
        place.setStartTime(new Time(1));
        place.setDay(nd);
        place.setId(1);
        HashSet<Place> pl = new HashSet<>();
        pl.add(place);
        nd.setAgenda(ar.getAgenda());
        nd.setPlaces(pl);
        nd.setId(1);
        nd.setStartHour(1);
        nd.setcDate(new Date());
        WeatherApiResponse war = new WeatherApiResponse();
        nd.setWeather(war);
        hs.add(nd);
        ar.setAgenda(new Agenda());
        ar.setEmail("vasile@email.com");
        ar.getAgenda().setDays(hs);
        ar.getAgenda().setPlaces(pl);
        HashSet<Preference> preferences = new HashSet<>();
        Preference pref = new Preference();
        pref.setAgenda(ar.getAgenda());
        pref.setId(1);
        pref.setPreferinta("maraciuca");
        preferences.add(pref);
        ar.getAgenda().setPreferences(preferences);
        ar.getAgenda().setUser(new User());
        HashSet<CheckList> checkLists = new HashSet<>();
        CheckList checkList = new CheckList();
        checkList.setAgenda(ar.getAgenda());
        checkList.setChecked(false);
        checkList.setComment("nothing");
        checkList.setId(1);
        checkLists.add(checkList);
        ar.getAgenda().setCheckLists(checkLists);
        ar.getAgenda().setEndDate(new Date());
        ar.getAgenda().setId(1);
        ar.getAgenda().setLocation("Berlin");
        ar.getAgenda().setSaveDate(new Date());
        ar.getAgenda().setStartDate(new Date());

        return ar;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/agendaup")
    public AgendaControllerResponse agendaUp(@RequestBody final AgendaEmailRequestBody agendaEmailRequestBody) throws Exception {
        Agenda agenda = agendaEmailRequestBody.getAgenda();
        String email = agendaEmailRequestBody.getEmail();
        Agenda agendaDB = agendaRepository.findById(agenda.getId());
        AgendaDiff.DiffType diffType;
        int i = 0;
        List<WeatherApiResponse> weatherApiResponses = new ArrayList<>();
        if(agendaDB != null){
            diffType = agendaDiff(agenda,agendaDB);
            switch (diffType){
                case MODIFIED:
                    agenda.setDays(planDays(createDays(agenda.getStartDate(),agenda.getEndDate(), agenda.getDays())));
                    break;
                case CALL_PLACES:
                    agenda.setDays(planDays(createDays(agenda.getStartDate(),agenda.getEndDate(), agenda.getDays())));
                    agenda.setPreferences(agendaDB.getPreferences());
                    agenda.setPlaces(preferedPlaces(agenda.getLocation(),agenda));

                    agendaRepository.save(agenda);
                    break;
                case CALL_WEATHER:
                    agenda.setDays(planDays(createDays(agenda.getStartDate(),agenda.getEndDate(), agenda.getDays())));
                    weatherApiResponses = getWeather(agenda.getLocation(),agenda.getStartDate(),agenda.getEndDate());
                    i = 0;
                    for(Day day : agenda.getDays()){
                        day.setWeather(weatherApiResponses.get(i++));
                    }
                    agendaRepository.save(agenda);
                    break;
                case CALL_BOTH:
                    agenda.setDays(planDays(createDays(agenda.getStartDate(),agenda.getEndDate(), agenda.getDays())));
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
