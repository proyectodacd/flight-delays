
package com.duodinamico.controller.apiconsumer;

import com.duodinamico.model.Flight;

import java.util.List;

public class AviationStackProvider implements FlightProvider {

    @Override
    public List<Flight> flightProvider(String[] args) {
        AviationStackProcessor aviationStackProcessor = new AviationStackProcessor();
        FlightDeserializer flightDeserializer = new FlightDeserializer();
        return flightDeserializer.flightDeserializer(aviationStackProcessor.petitionValidator(aviationStackProcessor.flightsPetition(args),args));
    }
}

