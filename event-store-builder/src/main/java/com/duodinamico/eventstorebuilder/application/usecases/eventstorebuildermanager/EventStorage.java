package com.duodinamico.eventstorebuilder.application.usecases.eventstorebuildermanager;

import com.google.gson.JsonParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EventStorage {

    private EventsFilePathGenerator filePathGenerator;

    public EventStorage(EventsFilePathGenerator filePathGenerator) {
        this.filePathGenerator = filePathGenerator;
    }

    public void saveToEventsFile(String json, String topic) {
        try {
            String path = topic.equals("Flights") ? this.filePathGenerator.getFlightsFilePathForWriting(JsonParser.parseString(json).getAsJsonObject()) : this.filePathGenerator.getWeatherFilePathForWriting(JsonParser.parseString(json).getAsJsonObject()) ;
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
