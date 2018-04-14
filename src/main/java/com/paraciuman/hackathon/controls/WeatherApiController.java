package com.paraciuman.hackathon.controls;

import com.paraciuman.hackathon.model.Loc;
import com.paraciuman.hackathon.responseTypes.WeatherApiResponse;
import org.apache.tomcat.util.bcel.Const;
import org.hibernate.boot.jaxb.SourceType;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.paraciuman.hackathon.requestBodyTypes.WeatherAPIRequestBody;


import java.text.SimpleDateFormat;
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

    @GetMapping(path = "/weather1")
    public WeatherAPIRequestBody getWeatherAPIRequestBody() {
        return new WeatherAPIRequestBody("Berlin", "14/05/2018", "16/05/2018");
    }

    @PostMapping(path = "/weather")
//    public List<WeatherApiResponse> postWeather
    public Date postWeather
        (@RequestBody final WeatherAPIRequestBody weatherAPIRequestBody)
        throws Exception {
        List<WeatherApiResponse> response = new ArrayList<>();

        Loc loc = HttpActions.getLatLong(weatherAPIRequestBody.getLocation());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date endDate = sdf.parse(weatherAPIRequestBody.getEndDate());
        Date startDate = sdf.parse(weatherAPIRequestBody.getStartDate());

        return startDate;
//        Integer i = 0;
//
//        while (true) {
//            String mounth, day;
//            Date newDate = DateUtil.addDays(startDate, i);
//            i++;
//            if (newDate.after(endDate))
//                break;
//
//            if (newDate.getDay() < 10)
//                day = "0" + newDate.getDay();
//            else
//                day = String.valueOf(newDate.getDay());
//
//            if (newDate.getMonth() < 10)
//                mounth = "0" + newDate.getMonth();
//            else
//                mounth = String.valueOf(newDate.getMonth());
//
//            JSONObject json = HttpActions.getHTML("https://api.darksky.net/forecast/" + KEY +"/" + loc.getLat() + "," + loc.getLng() +
//                    "," + newDate.getYear() + "-" + mounth + "-" + day + "T12:00:00"
//            );
//
//            WeatherApiResponse weatherApiResponse = new WeatherApiResponse(
//                    json.getJSONObject("currently").getString("summary"),
//                    json.getJSONObject("currently").getDouble("temperature"),
//                    json.getJSONObject("currently").getString("icon")
//            );
//
//            response.add(weatherApiResponse);
//        }
//        return response;
    }


}
