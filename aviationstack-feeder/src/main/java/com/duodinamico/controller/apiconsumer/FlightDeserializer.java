package com.duodinamico.controller.apiconsumer;
import com.duodinamico.controller.FlightMapper;
import com.duodinamico.controller.model.FlightModel;
import com.duodinamico.controller.apiconsumer.schema.Flight;

import com.duodinamico.controller.apiconsumer.schema.FlightResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class FlightDeserializer {

    FlightMapper mapper = new FlightMapper();

    ArrayList<FlightModel> flightList = new ArrayList<>();

    public ArrayList<FlightModel> flightDeserializer(String json) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FlightResponse response = gson.fromJson(json, FlightResponse.class);

        for (Flight flight: response.getData() ) {
            flightList.add(mapper.getFlightModel(flight));
        }
        return flightList;
    }
}
