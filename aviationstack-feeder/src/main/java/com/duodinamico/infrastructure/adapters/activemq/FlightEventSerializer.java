package com.duodinamico.infrastructure.adapters.activemq;
import com.duodinamico.domain.model.FlightEvent;
import com.google.gson.Gson;

public class FlightEventSerializer {
    private final Gson gson = new Gson();

    public String serializeFlightEvent(FlightEvent flightEvent) {
        return gson.toJson(flightEvent);
    }
}

