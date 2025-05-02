package com.duodinamico.controller.apiconsumer;

import com.duodinamico.controller.apiconsumer.schema.WeatherResponse;
import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.controller.persistency.Coordinates;
import com.duodinamico.controller.model.WeatherResult;

public interface WeatherProvider {
    WeatherResponse weatherProvider(Coordinates coordinates, String time);
}
