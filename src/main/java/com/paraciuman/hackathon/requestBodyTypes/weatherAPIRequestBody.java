package com.paraciuman.hackathon.requestBodyTypes;

import java.util.Date;

public class WeatherAPIRequestBody {
    String location;
    String startDate;
    String endDate;

    public WeatherAPIRequestBody() {
    }

    public WeatherAPIRequestBody(String location, String startDate, String endDate) {
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
