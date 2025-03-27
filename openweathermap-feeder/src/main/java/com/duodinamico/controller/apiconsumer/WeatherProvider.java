package com.duodinamico.controller.apiconsumer;

import com.duodinamico.model.WeatherResult;

import java.util.List;

public interface WeatherProvider {
    WeatherResult weatherProvider(String latitud, String longitud, String time, String[] args);

}
