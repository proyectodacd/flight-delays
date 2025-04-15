package com.duodinamico.controller.apiconsumer;

import com.duodinamico.controller.persistency.Coordinates;
import com.duodinamico.model.WeatherResult;

import java.util.List;

public interface WeatherProvider {
    WeatherResult weatherProvider(Coordinates coordinates, String time);

}
