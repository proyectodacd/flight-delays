package com.duodinamico.eventstorebuilder.tools;

import com.duodinamico.aviationstackfeeder.domain.model.FlightEvent;
import com.google.gson.Gson;

public class FlightEventDeserializer {
    private final Gson gson = new Gson();

    public FlightEvent deserializeFlightEvent(String json) {
        return gson.fromJson(json, FlightEvent.class);
    }
}
