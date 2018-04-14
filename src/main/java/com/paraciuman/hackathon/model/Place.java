package com.paraciuman.hackathon.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_agenda")
    private Agenda agenda;

    @Nullable
    private Date startDate;

    @Nullable
    private Date endDate;

    private Date day;

    private Time travelToNextPOI;


    @Nullable
    private Integer estimation;

    public Place() {
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Time getTravelToNextPOI() {
        return travelToNextPOI;
    }

    public void setTravelToNextPOI(Time travelToNextPOI) {
        this.travelToNextPOI = travelToNextPOI;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getEstimation() {
        return estimation;
    }

    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
    }
}
