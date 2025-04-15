
package com.duodinamico.controller.apiconsumer;

import com.duodinamico.model.FlightModel;

import java.util.ArrayList;

public class AviationStackProvider implements FlightProvider {

    private final String[] apiKeys;

    public AviationStackProvider(String[] apiKeys) {
        this.apiKeys = apiKeys;
    }

    public String[] getApiKeys() {
        return apiKeys;
    }

    @Override
    public ArrayList<FlightModel> flightProvider() {
        AviationStackProcessor aviationStackProcessor = new AviationStackProcessor(getApiKeys());
        FlightDeserializer flightDeserializer = new FlightDeserializer();
        return flightDeserializer.flightDeserializer(aviationStackProcessor.petitionValidator(aviationStackProcessor.flightsPetition()));
    }

}

