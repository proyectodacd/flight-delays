package com.duodinamico.flightdelayestimator.datamart.history;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherEventHistoryLoader {

    private final File directory;

    public WeatherEventHistoryLoader(String directoryPath) {
        this.directory = new File(directoryPath);
    }

    public Map<File, List<JsonObject>> loadWeatherEventsFromFiles() throws IOException {
        Map<File, List<JsonObject>> result = new HashMap<>();

        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Directorio no válido: " + directory.getAbsolutePath());
        }

        File[] files = directory.listFiles((dir, name) -> name.endsWith(".events"));
        if (files == null) return result;

        for (File file : files) {
            List<JsonObject> events = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    JsonObject event = JsonParser.parseString(line).getAsJsonObject();
                    events.add(event);
                }
            }
            result.put(file, events);
        }
        return result;
    }
}

