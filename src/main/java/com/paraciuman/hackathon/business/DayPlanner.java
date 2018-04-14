package com.paraciuman.hackathon.business;

import com.paraciuman.hackathon.model.Day;

import java.sql.Time;
import java.util.Set;

public class DayPlanner {
    public Set<Day> planDays(Set<Day> unplannedDays) {


        for (Day day : unplannedDays) {
            int myTime = day.getStartHour();
            Time time = new Time();
        }

        return unplannedDays;
    }
}
