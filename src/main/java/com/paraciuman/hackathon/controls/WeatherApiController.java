package com.paraciuman.hackathon.controls;

import com.paraciuman.hackathon.model.Loc;
import org.apache.tomcat.util.bcel.Const;
import org.hibernate.boot.jaxb.SourceType;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.util.*;

@RestController
public class WeatherApiController {

    private static final String KEY = "f8b16921c9ffdcb6e3b292741a9e44ae";

    public static class DateUtil
    {
        public static Date addDays(Date date, int days)
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, days); //minus number would decrement the days
            return cal.getTime();
        }

        public static Date subtractDays(Date date, int days) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(date);
            cal.add(Calendar.DATE, -days);

            return cal.getTime();
        }
    }

    @GetMapping(path = "/weather")
    static String getWeather() throws Exception {
        Date startDate = new Date(2018, 5, 14);
        Date endDate = new Date(2018, 5, 16);
        String location = "Berlin";
        String response = "123";

        List<Date> dates = new ArrayList<>(25);
        dates.add(startDate);

        Loc loc = HttpActions.getLatLong(location);

        Integer i = 0;
        while (true) {
            Date newDate = DateUtil.addDays(startDate, i);
            i++;
            if (newDate.after(endDate))
                break;

            JSONObject json = HttpActions.getHTML("https://api.darksky.net/forecast/" + KEY +"/" + loc.getLat() + "," + loc.getLng() +
                    "[" +newDate.getYear() + "]-[" + newDate.getMonth() + "]-[" + newDate.getDay() + "]T[12]:[00]:[00]"
            );

            System.out.println(json.toString());
        }


        return "res";
    }

    public static void main(String[] args) {
        try {
            getWeather();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
