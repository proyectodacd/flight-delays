package com.duodinamico.controller.apiconsumer;
import com.duodinamico.model.FlightModel;
import com.duodinamico.model.schema.Flight;

import com.duodinamico.model.schema.FlightResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class FlightDeserializer {

    ArrayList<FlightModel> flightList = new ArrayList<>();

    public ArrayList<FlightModel> flightDeserializer(String json) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FlightResponse response = gson.fromJson(json, FlightResponse.class);

        for (Flight flight: response.getData() ) {
            flightList.add(new FlightModel(flight));
        }
        return flightList;
    }
}
