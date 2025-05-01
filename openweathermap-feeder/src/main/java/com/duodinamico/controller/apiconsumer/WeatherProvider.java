package com.duodinamico.controller.apiconsumer;

import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.controller.persistency.Coordinates;
import com.duodinamico.controller.model.WeatherResult;

public interface WeatherProvider {
    WeatherResult weatherProvider(Coordinates coordinates, String time);
    WeatherEvent weatherEventProvider(Coordinates coordinates, String time);

}
