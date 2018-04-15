package com.paraciuman.hackathon.model;

import com.paraciuman.hackathon.model.Day;
import org.hibernate.mapping.Join;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherApiResponse)) return false;
        WeatherApiResponse that = (WeatherApiResponse) o;
        return Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getTemperature(), that.getTemperature()) &&
                Objects.equals(getIcon(), that.getIcon()) &&
                Objects.equals(getDay(), that.getDay());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getDescription(), getTemperature(), getIcon(), getDay());
    }
}
