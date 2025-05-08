package com.duodinamico.infrastructure.adapters.activemq;

import com.duodinamico.domain.model.FlightEvent;
import com.google.gson.Gson;
import java.io.*;
import java.util.*;

public class FlightEventsFileReader {
    FlightEventDeserializer flightEventDeserializer = new FlightEventDeserializer();
    Gson gson = new Gson();
    ArrayList<FlightEvent> flightEventList = new ArrayList<>();
    EventsFilePathGeneratorForReading eventsFilePathGeneratorForReading = new EventsFilePathGeneratorForReading();


    public ArrayList<FlightEvent> extractFlightEventsFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(eventsFilePathGeneratorForReading.getEventFilePathForReading(1)))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                FlightEvent flightEvent = flightEventDeserializer.deserializeFlightEvent(linea);
                flightEventList.add(flightEvent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flightEventList;
    }
}

