package com.duodinamico.controller;

import com.duodinamico.controller.apiconsumer.schema.WeatherInformation;
import com.duodinamico.controller.apiconsumer.schema.WeatherResponse;
import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.controller.model.WeatherResult;

import java.util.ArrayList;

public class WeatherResultMapper {

    public ArrayList<WeatherResult> getWeatherResult(WeatherResponse weatherResponse, String city) {
        ArrayList<WeatherResult> weatherResultArrayList = new ArrayList<>();
        for (WeatherInformation weatherInformation : weatherResponse.getList()){
            WeatherResult weatherResult = new WeatherResult(city,
                    weatherInformation.getDataCalculationTime(),
                    weatherInformation.getThermalConditions().getTemperature(),
                    weatherInformation.getThermalConditions().getFeelsLike(),
                    weatherInformation.getWind().getSpeed(),
                    weatherInformation.getWind().getDeg(),
                    weatherInformation.getClouds().getCloudiness(),
                    weatherInformation.getRain().getRainOneHour(),
                    weatherInformation.getSnow().getSnowOneHour(),
                    weatherInformation.getDescription().getFirst().getDescription());
            weatherResultArrayList.add(weatherResult);
        }
        return weatherResultArrayList;
    }

}
