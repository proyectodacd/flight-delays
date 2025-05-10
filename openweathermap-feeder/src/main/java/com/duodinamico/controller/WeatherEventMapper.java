package com.duodinamico.controller;

import com.duodinamico.controller.apiconsumer.schema.WeatherInformation;
import com.duodinamico.controller.apiconsumer.schema.WeatherResponse;
import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.controller.model.WeatherResult;
import com.duodinamico.controller.persistency.UnixToUTCDateFormatter;

import java.util.ArrayList;

public class WeatherEventMapper {
    public ArrayList<WeatherEvent> getWeatherEvent(WeatherResponse weatherResponse, String city) {
        ArrayList<WeatherEvent> weatherEventArrayList = new ArrayList<>();
        UnixToUTCDateFormatter unixToUTCDateFormatter = new UnixToUTCDateFormatter();
        for (WeatherInformation weatherInformation : weatherResponse.getList()){
            WeatherEvent weatherEvent = new WeatherEvent(city,
                    weatherInformation.getDataCalculationTime(),
                    unixToUTCDateFormatter.formatUnixSecondsToISO8601UTC(weatherInformation.getDataCalculationTime()),
                    weatherInformation.getThermalConditions().getTemperature(),
                    weatherInformation.getThermalConditions().getFeelsLike(),
                    weatherInformation.getWind().getSpeed(),
                    weatherInformation.getWind().getDeg(),
                    weatherInformation.getClouds().getCloudiness(),
                    weatherInformation.getRain().getRainOneHour(),
                    weatherInformation.getSnow().getSnowOneHour(),
                    weatherInformation.getDescription().getFirst().getDescription());
            weatherEventArrayList.add(weatherEvent);
        }
        return weatherEventArrayList;
    }
}
