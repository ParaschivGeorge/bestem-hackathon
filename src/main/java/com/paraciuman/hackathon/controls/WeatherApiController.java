package com.paraciuman.hackathon.controls;

import com.paraciuman.hackathon.model.Loc;
import com.paraciuman.hackathon.model.WeatherApiResponse;
import org.json.JSONObject;


import java.util.*;

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

    public static List<WeatherApiResponse> getWeather(String location, Date startDate, Date endDate)
        throws Exception {
        List<WeatherApiResponse> response = new ArrayList<>();

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

}
