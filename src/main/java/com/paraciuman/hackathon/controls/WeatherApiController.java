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
    public List<WeatherApiResponse> getWeather() throws Exception {
        Date startDate = new Date(2018, 4, 14);
        Date endDate = new Date(2018, 4, 16);
        String location = "Berlin";
        List<WeatherApiResponse> response = new ArrayList<>();
        List<Date> dates = new ArrayList<>(25);
        dates.add(startDate);

        Loc loc = HttpActions.getLatLong(location);

        Integer i = 0;
        while (true) {
            String mounth, day;
            Date newDate = DateUtil.addDays(startDate, i);
            i++;
            if (newDate.after(endDate))
                break;

            if (newDate.getDay() < 10)
                day = "0" + newDate.getDay();
            else
                day = String.valueOf(newDate.getDay());

            if (newDate.getMonth() < 10)
                mounth = "0" + newDate.getMonth();
            else
                mounth = String.valueOf(newDate.getMonth());

            JSONObject json = HttpActions.getHTML("https://api.darksky.net/forecast/" + KEY +"/" + loc.getLat() + "," + loc.getLng() +
                    "," + newDate.getYear() + "-" + mounth + "-" + day + "T12:00:00"
            );

            WeatherApiResponse weatherApiResponse = new WeatherApiResponse(
                    json.getJSONObject("currently").getString("summary"),
                    json.getJSONObject("currently").getDouble("temperature"),
                    json.getJSONObject("currently").getString("icon")
            );

            response.add(weatherApiResponse);
        }
        return response;
    }

    private class WeatherApiResponse {
        private String description;
        private Double temperature;
        private String icon;

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
}
