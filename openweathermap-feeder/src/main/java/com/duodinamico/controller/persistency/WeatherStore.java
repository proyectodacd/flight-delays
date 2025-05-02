package com.duodinamico.controller.persistency;

import com.duodinamico.domain.model.FlightModel;

public interface WeatherStore {

    public void saveWeather (FlightModel flight);

}
