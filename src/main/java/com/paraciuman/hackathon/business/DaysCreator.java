package com.paraciuman.hackathon.business;

import com.paraciuman.hackathon.model.Day;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DaysCreator {
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static Set<Day> createDays(Date startDate, Date endDate, Set<Day> days) {
        HashSet<Day> newDays = new HashSet<>();

        Integer i = 0;

        while (true) {
            Date newDate = addDays(startDate, i);
            i++;
            if (newDate.after(endDate))
                break;
            Day theDay = containsDay(newDate.getYear(), newDate.getMonth(), newDate.getDay(),days);
            if (theDay != null) {
                Day newDay = new Day();
                newDay.setCurrentDate(newDate);
                newDays.add(newDay);
            }
            else
                newDays.add(theDay);
        }

        return newDays;
    }

    private static Day containsDay(int year, int month, int day, Set<Day> days) {
        for (Day myDay : days) {
            if ((myDay.getCurrentDate().getYear() == year) &&
                    (myDay.getCurrentDate().getMonth() == month) &&
                    (myDay.getCurrentDate().getDay() == day))
                return myDay;
        }
        return null;
    }
}
