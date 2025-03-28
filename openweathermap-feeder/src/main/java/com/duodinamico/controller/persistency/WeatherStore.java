package com.duodinamico.controller.persistency;

import com.duodinamico.model.Flight;
import com.duodinamico.model.WeatherResult;

public interface WeatherStore {

    public void saveWeather (Flight flight, String[] args);

}
