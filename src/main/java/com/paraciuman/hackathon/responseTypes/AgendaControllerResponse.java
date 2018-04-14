package com.paraciuman.hackathon.responseTypes;

import com.paraciuman.hackathon.model.Day;
import com.paraciuman.hackathon.model.Place;

import java.util.List;

public class AgendaControllerResponse {
    List<Place> places;
    List<Day> days;

    public AgendaControllerResponse() {}

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
