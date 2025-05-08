package com.duodinamico.weather;

import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.flight.EventsFilePathGeneratorForWriting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeatherEventStorage {

    private EventsFilePathGeneratorForWriting eventsFilePathGeneratorForWriting;

    public WeatherEventStorage(EventsFilePathGeneratorForWriting eventsFilePathGeneratorForWriting) {
        this.eventsFilePathGeneratorForWriting = eventsFilePathGeneratorForWriting;
    }

    public void saveWeatherToEventsFile(String json, WeatherEvent weatherEvent) {
        try {
            String path = this.eventsFilePathGeneratorForWriting.getWeatherFilePathForWriting(0,weatherEvent);
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
