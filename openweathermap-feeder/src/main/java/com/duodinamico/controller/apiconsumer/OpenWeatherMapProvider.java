package com.duodinamico.controller.apiconsumer;

import com.duodinamico.model.WeatherResult;

import java.util.List;

public class OpenWeatherMapProvider  implements WeatherProvider{

    @Override
    public WeatherResult weatherProvider(String latitud, String longitud, String time, String[] args) {
        OpenWeatherMapProcessor openWeatherMapProcessor = new OpenWeatherMapProcessor();
        WeatherDeserializer weatherDeserializer = new WeatherDeserializer();
        return weatherDeserializer.weatherDeserializer(openWeatherMapProcessor.weatherPetition(latitud, longitud, time, args));
    }

}
