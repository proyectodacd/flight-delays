package com.duodinamico.flightdelayestimator.infrastructure.datamart.history;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.*;

public class WeatherEventHistoryLoader {

    private final File directory;

    public WeatherEventHistoryLoader(String directoryPath) {
        this.directory = new File(directoryPath);
    }

    public Map<File, List<JsonObject>> loadWeatherEventsFromFiles() throws IOException {
        validateDirectory();
        Map<File, List<JsonObject>> result = new HashMap<>();
        File[] files = getEventFiles();
        for (File file : files) {
            List<JsonObject> events = readEventsFromFile(file);
            result.put(file, events);
        }
        return result;
    }

    private void validateDirectory() {
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Directorio no válido: " + directory.getAbsolutePath());
        }
    }

    private File[] getEventFiles() {
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".events"));
        return files != null ? files : new File[0];
    }

    private List<JsonObject> readEventsFromFile(File file) throws IOException {
        List<JsonObject> events = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                JsonObject event = JsonParser.parseString(line).getAsJsonObject();
                events.add(event);
            }
        }
        return events;
    }
}
