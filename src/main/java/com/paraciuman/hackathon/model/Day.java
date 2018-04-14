package com.paraciuman.hackathon.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Day {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Place> places;

    private Integer startHour;

    @OneToOne
    @JoinColumn(name = "id_weatherApiResponse")
    private WeatherApiResponse weather;
    private Date currentDate;

    @ManyToOne
    @JoinColumn(name = "id_agenda")
    private Agenda agenda;

    public Day() {
    }

    public Day(Set<Place> places, int hour) {
        this.places = places;
        this.startHour = hour;
    }

    public Set<Place> getPlaces() {

        return places;
    }

    public WeatherApiResponse getWeather() {
        return weather;
    }

    public void setWeather(WeatherApiResponse weather) {
        this.weather = weather;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }


    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
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
