package com.duodinamico.controller.apiconsumer;
import com.duodinamico.controller.FlightMapper;
import com.duodinamico.controller.eventIntegration.FlightEvent;
import com.duodinamico.controller.model.FlightModel;
import com.duodinamico.controller.apiconsumer.schema.Flight;

import com.duodinamico.controller.apiconsumer.schema.FlightResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class FlightDeserializer {

    FlightMapper mapper = new FlightMapper();

    public ArrayList<FlightModel> flightDeserializer(String json) {
        ArrayList<FlightModel> flightList = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FlightResponse response = gson.fromJson(json, FlightResponse.class);

        for (Flight flight: response.getData() ) {
            flightList.add(mapper.getFlightModel(flight));
        }
        return flightList;
    }

    public ArrayList<FlightEvent> flightDeserializerForEvents(String json) {
        ArrayList<FlightEvent> flightList = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FlightResponse response = gson.fromJson(json, FlightResponse.class);

        for (Flight flight: response.getData() ) {
            flightList.add(mapper.getFlightEvent(flight));
        }
        return flightList;
    }
}
