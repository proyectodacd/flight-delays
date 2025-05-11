package com.duodinamico;

import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.controller.eventintegration.WeatherEventDeserializer;
import com.duodinamico.domain.model.FlightEvent;
import com.duodinamico.infrastructure.adapters.activemq.FlightEventDeserializer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EventStorage {

    private EventsFilePathGeneratorForWriting filePathGenerator;
    private FlightEventDeserializer flightEventDeserializer;
    private WeatherEventDeserializer weatherEventDeserializer;

    public EventStorage(EventsFilePathGeneratorForWriting filePathGenerator) {
        this.filePathGenerator = filePathGenerator;
        this.flightEventDeserializer = new FlightEventDeserializer();
        this.weatherEventDeserializer = new WeatherEventDeserializer();
    }

    public void saveToEventsFile(String json, String topic) {
        try {
            String path = topic.equals("Flights") ? this.filePathGenerator.getFlightsFilePathForWriting(this.flightEventDeserializer.deserializeFlightEvent(json)) : this.filePathGenerator.getWeatherFilePathForWriting(this.weatherEventDeserializer.deserializeWeatherEvent(json)) ;
            File file = new File(path);
            file.getParentFile().mkdirs();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(json);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
