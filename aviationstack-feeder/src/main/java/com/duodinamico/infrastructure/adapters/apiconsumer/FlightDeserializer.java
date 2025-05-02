package com.duodinamico.infrastructure.adapters.apiconsumer;
import com.duodinamico.infrastructure.adapters.mappers.FlightModelMapper;
import com.duodinamico.domain.model.FlightEvent;
import com.duodinamico.infrastructure.adapters.apiconsumer.schema.Flight;

import com.duodinamico.infrastructure.adapters.apiconsumer.schema.FlightResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class FlightDeserializer {

    public FlightResponse flightDeserializer(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FlightResponse response = gson.fromJson(json, FlightResponse.class);

        return response;
    }
}
