package com.duodinamico.controller.persistency;

import com.duodinamico.domain.model.FlightEvent;
import com.duodinamico.domain.model.FlightModel;
import java.util.ArrayList;

public interface WeatherStore {

    void saveDepartureWeather (ArrayList<FlightEvent> flights);
    void saveArrivalWeather (ArrayList<FlightEvent> flights);

}
