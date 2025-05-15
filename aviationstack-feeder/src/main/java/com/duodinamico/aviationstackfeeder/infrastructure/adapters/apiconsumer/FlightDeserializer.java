package com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer;

import com.duodinamico.aviationstackfeeder.domain.schema.FlightResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FlightDeserializer {

    public FlightResponse flightDeserializer(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FlightResponse response = gson.fromJson(json, FlightResponse.class);

        return response;
    }
}
