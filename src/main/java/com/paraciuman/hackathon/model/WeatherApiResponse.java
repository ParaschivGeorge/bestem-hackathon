package com.paraciuman.hackathon.model;

import com.paraciuman.hackathon.model.Day;
import org.hibernate.mapping.Join;

import javax.persistence.*;

@Entity
public class WeatherApiResponse {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;
    private String description;
    private Double temperature;
    private String icon;
    @OneToOne
    @JoinColumn(name = "id_day")
    private Day day;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public WeatherApiResponse() {
    }

    public WeatherApiResponse(String description, Double temperature, String icon) {
        this.description = description;
        this.temperature = temperature;
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
