package com.paraciuman.hackathon.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date cDate;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Place> places;

    private Integer startHour;

    @OneToOne
    @JoinColumn(name = "id_war")
    private WeatherApiResponse weather;


    @ManyToOne
    @JoinColumn(name = "id_agenda")
    private Agenda agenda;

    public Day() {
    }

    /*public Day(Set<Place> places, int hour) {
        this.places = places;
        this.startHour = hour;
    }
*/
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

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date currentDate) {
        this.cDate = currentDate;
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
        if (!(o instanceof Day)) return false;
        Day day = (Day) o;
        return Objects.equals(getcDate(), day.getcDate()) &&
                Objects.equals(getPlaces(), day.getPlaces()) &&
                Objects.equals(getStartHour(), day.getStartHour()) &&
                Objects.equals(getWeather(), day.getWeather()) &&
                Objects.equals(getAgenda(), day.getAgenda());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getcDate(), getPlaces(), getStartHour(), getWeather(), getAgenda());
    }
}
