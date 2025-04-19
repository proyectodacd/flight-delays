
package com.duodinamico.controller.apiconsumer;

import com.duodinamico.controller.eventIntegration.FlightEvent;
import com.duodinamico.controller.model.FlightModel;

import java.util.ArrayList;

public class AviationStackProvider implements FlightProvider {

    private final String[] apiKeys;

    public AviationStackProvider(String[] apiKeys) {
        this.apiKeys = apiKeys;
    }

    @Override
    public ArrayList<FlightModel> flightProvider() {
        AviationStackProcessor aviationStackProcessor = new AviationStackProcessor(this.apiKeys);
        FlightDeserializer flightDeserializer = new FlightDeserializer();
        return flightDeserializer.flightDeserializer(aviationStackProcessor.petitionValidator(aviationStackProcessor.flightsPetition()));
    }

    @Override
    public ArrayList<FlightEvent> flightEventProvider() {
        AviationStackProcessor aviationStackProcessor = new AviationStackProcessor(this.apiKeys);
        FlightDeserializer flightDeserializer = new FlightDeserializer();
        return flightDeserializer.flightDeserializerForEvents(aviationStackProcessor.petitionValidator(aviationStackProcessor.flightsPetition()));
    }

}

