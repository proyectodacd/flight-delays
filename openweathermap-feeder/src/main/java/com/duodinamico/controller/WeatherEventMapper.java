package com.duodinamico.controller;

import com.duodinamico.controller.apiconsumer.schema.WeatherResponse;
import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.domain.model.FlightModel;

public class WeatherEventMapper {
    public WeatherEvent getWeatherEvent(FlightModel flightModel, WeatherResponse weatherResponse) {
        WeatherEvent weatherEvent = new WeatherEvent(
                flightModel.getFlightIcao(),
                flightModel.getFlightDate(),
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
        return weatherEvent;
    }
}
