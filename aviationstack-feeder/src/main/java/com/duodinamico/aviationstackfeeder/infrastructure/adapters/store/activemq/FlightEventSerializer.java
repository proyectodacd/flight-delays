package com.duodinamico.aviationstackfeeder.infrastructure.adapters.store.activemq;
import com.duodinamico.aviationstackfeeder.domain.model.FlightEvent;
import com.google.gson.Gson;

public class FlightEventSerializer {
    private final Gson gson = new Gson();

    public String serializeFlightEvent(FlightEvent flightEvent) {
        return gson.toJson(flightEvent);
    }
}

