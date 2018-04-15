package com.paraciuman.hackathon.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_agenda")
    private Agenda agenda;

    @Nullable
    private Time startTime;

    @Nullable
    private Time endTime;

    @Nullable
    private Time estimation;

    private Time travelToNextPOI;

    private String Name;

    private String photoUrl;



    @ManyToOne
    @JoinColumn(name = "id_day")
    private Day day;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    //private



    public Place() {
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Time getTravelToNextPOI() {
        return travelToNextPOI;
    }

    public void setTravelToNextPOI(Time travelToNextPOI) {
        this.travelToNextPOI = travelToNextPOI;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Time getEstimation() {
        return estimation;
    }

    public void setEstimation(Time estimation) {
        this.estimation = estimation;
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
        if (!(o instanceof Place)) return false;
        Place place = (Place) o;
        return Objects.equals(getAgenda(), place.getAgenda()) &&
                Objects.equals(getStartTime(), place.getStartTime()) &&
                Objects.equals(getEndTime(), place.getEndTime()) &&
                Objects.equals(getEstimation(), place.getEstimation()) &&
                Objects.equals(getTravelToNextPOI(), place.getTravelToNextPOI()) &&
                Objects.equals(getName(), place.getName()) &&
                Objects.equals(getPhotoUrl(), place.getPhotoUrl()) &&
                Objects.equals(getDay(), place.getDay());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAgenda(), getStartTime(), getEndTime(), getEstimation(), getTravelToNextPOI(), getName(), getPhotoUrl(), getDay());
    }
}
