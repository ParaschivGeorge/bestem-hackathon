package com.paraciuman.hackathon.requestBodyTypes;

import java.util.Date;

public class WeatherAPIRequestBody {
    String location;
    Date startDate;
    Date endDate;

    public WeatherAPIRequestBody() {
    }

    public WeatherAPIRequestBody(String location, Date startDate, Date endDate) {
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
}
