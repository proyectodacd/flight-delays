package com.duodinamico.controller.apiconsumer;
import com.duodinamico.model.Flight;

import com.duodinamico.model.schema.FlightResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class FlightDeserializer {

    public List<Flight> flightDeserializer(String json) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FlightResponse response = gson.fromJson(json, FlightResponse.class);

        return response.getData();

    }
}
