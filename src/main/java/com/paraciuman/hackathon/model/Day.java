package com.paraciuman.hackathon.model;

import com.paraciuman.hackathon.responseTypes.WeatherApiResponse;

import java.util.List;
import java.util.Objects;

public class Day {
    private List<Place> places;
    private int startHour;
    private WeatherApiResponse weather;

    public Day(List<Place> places, int hour) {
        this.places = places;
        this.startHour = hour;
    }

    public List<Place> getPlaces() {

        return places;
    }

    public WeatherApiResponse getWeather() {
        return weather;
    }

    public void setWeather(WeatherApiResponse weather) {
        this.weather = weather;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return startHour == day.startHour &&
                Objects.equals(places, day.places);
    }

    @Override
    public int hashCode() {

        return Objects.hash(places, startHour);
    }

}
