package com.paraciuman.hackathon.responseTypes;

public class WeatherApiResponse {
    private String description;
    private Double temperature;
    private String icon;

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
