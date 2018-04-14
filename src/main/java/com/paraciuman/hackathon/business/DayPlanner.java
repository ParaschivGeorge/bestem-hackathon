package com.paraciuman.hackathon.business;

import com.paraciuman.hackathon.model.Day;
import com.paraciuman.hackathon.model.Place;

import java.sql.Time;
import java.util.Set;

public class DayPlanner {
    public Set<Day> planDays(Set<Day> unplannedDays) {

        for (Day day : unplannedDays) {
            int startHour = day.getStartHour();
            Time currentTime = new Time(startHour, 0,0);
            for (Place place : day.getPlaces()) {
                place.setEstimation(new Time(0, 45,0)); // estimate here or call api
                place.setStartTime(currentTime);
                currentTime = new Time(place.getEstimation().getHours() + currentTime.getHours(),
                        place.getEstimation().getMinutes() + currentTime.getMinutes(),
                        place.getEstimation().getSeconds() + currentTime.getSeconds());
                place.setEndTime(currentTime);
                place.setTravelToNextPOI(new Time(0, 30,0)); // call google maps api
                currentTime = new Time(place.getTravelToNextPOI().getHours() + currentTime.getHours(),
                        place.getTravelToNextPOI().getMinutes() + currentTime.getMinutes(),
                        place.getTravelToNextPOI().getSeconds() + currentTime.getSeconds());
            }
        }

        return unplannedDays;
    }
}
