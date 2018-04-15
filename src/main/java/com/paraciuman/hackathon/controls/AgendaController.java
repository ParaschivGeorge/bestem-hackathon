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
import java.util.*;

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

    @GetMapping(path = "/agendaget")
    public AgendaEmailRequestBody aerb(){
        AgendaEmailRequestBody agendaEmailRequestBody = new AgendaEmailRequestBody();
        agendaEmailRequestBody.setEmail("vasile@mail.com");

        Agenda agenda = new Agenda();
        agenda.setId(123);
        agenda.setUser(null);
        agenda.setSaveDate(new Date());
        agenda.setStartDate(new Date(2018,5,14));
        agenda.setEndDate(new Date(2018,5,16));
        agenda.setLocation("Berlin");

        Set<Day> days = new HashSet<>();

        Set<Place> places = new HashSet<>();
        Place place = new Place();
        place.setId(1337);
        place.setAgenda(null);
        place.setStartTime(new Time(12,0,0));
        place.setEndTime(new Time(16,0,0));
        place.setEstimation(new Time(2,0,0));
        place.setTravelToNextPOI(new Time(0,30,0));
        place.setName("museum of art");
        place.setPhotoUrl("https://93546-d-c.ooyala.com/content/images/1140/1519923786_d980438c-2392-4a41-be08-bad3cda20504_636x357.jpg");
        place.setDay(null);
        places.add(place);
        agenda.setPlaces(places);

        Day day = new Day();
        day.setId(407);
        day.setcDate(new Date(2018,5,14));
        day.setPlaces(places);
        day.setAgenda(null);
        day.setStartHour(12);

        WeatherApiResponse weather = new WeatherApiResponse();
        weather.setDay(null);
        weather.setDescription("windy");
        weather.setIcon("windy-icon");
        weather.setTemperature(23.2);

        day.setWeather(weather);
        days.add(day);
        agenda.setDays(days);

        Set<Preference> preferences = new HashSet<>();
        Preference preference = new Preference();
        preference.setId(123123);
        preference.setAgenda(null);
        preference.setPreferinta("museum");
        preferences.add(preference);
        agenda.setPreferences(preferences);

        Set<CheckList> checkLists = new HashSet<>();
        CheckList checkList = new CheckList();
        checkList.setId(1231231);
        checkList.setComment("pick gheorghe from the museum");
        checkList.setChecked(false);
        checkList.setAgenda(null);
        checkLists.add(checkList);
        agenda.setCheckLists(checkLists);

        agendaEmailRequestBody.setAgenda(agenda);
        return agendaEmailRequestBody;
    }

    // function to set the many-to-one and the user from a JSON agenda
    private Agenda repairAgenda(Agenda agenda, String email) {
        User user = userRepository.findByEmail(email);
        agenda.setUser(user);

        for (Preference preference : agenda.getPreferences())
            preference.setAgenda(agenda);
        for (CheckList checkList : agenda.getCheckLists())
            checkList.setAgenda(agenda);
        for (Place place : agenda.getPlaces())
            place.setAgenda(agenda);
        for (Day day : agenda.getDays()) {
            day.setAgenda(agenda);
            for (Place place : day.getPlaces()) {
                place.setAgenda(agenda);
                place.setDay(day);
            }
            WeatherApiResponse weatherApiResponse = day.getWeather();
            weatherApiResponse.setDay(day);
        }
        return agenda;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/agendaup")
    public AgendaControllerResponse agendaUp(@RequestBody final AgendaEmailRequestBody agendaEmailRequestBody) throws Exception {
        Agenda agenda = agendaEmailRequestBody.getAgenda();
        String email = agendaEmailRequestBody.getEmail();
        agenda = repairAgenda(agenda, email);
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
