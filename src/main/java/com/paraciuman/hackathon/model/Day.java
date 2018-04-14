package com.paraciuman.hackathon.model;

import java.util.List;

public class Day {
    private List<Place> places;
    private int startHour;

    public Day(List<Place> places, int hour) {
        this.places = places;
        this.startHour = hour;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }
}
