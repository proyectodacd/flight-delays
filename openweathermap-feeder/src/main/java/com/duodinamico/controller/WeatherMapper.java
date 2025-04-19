package com.duodinamico.controller;

import com.duodinamico.controller.apiconsumer.schema.WeatherResponse;
import com.duodinamico.controller.model.WeatherResult;

public class WeatherMapper {

    public WeatherResult getWeatherResult(WeatherResponse weatherResponse) {
        WeatherResult weatherResult = new WeatherResult(
                weatherResponse.getList().getFirst().getTime(),
                weatherResponse.getList().getFirst().getThermalConditions().getTemperature(),
                weatherResponse.getList().getFirst().getThermalConditions().getFeelsLike(),
                weatherResponse.getList().getFirst().getWind().getSpeed(),
                weatherResponse.getList().getFirst().getWind().getDeg(),
                weatherResponse.getList().getFirst().getClouds().getCloudiness(),
                weatherResponse.getList().getFirst().getRain().getRainOneHour(),
                weatherResponse.getList().getFirst().getSnow().getSnowOneHour(),
                weatherResponse.getList().getFirst().getDescription().getFirst().getDescription()

        );
        return weatherResult;
    }

}
