package com.duodinamico.flightdelayestimator.datamart.history;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.*;
import java.util.*;

public class FlightEventHistoryLoader {

    private final File directory;

    public FlightEventHistoryLoader(String directoryPath) {
        this.directory = new File(directoryPath);
    }

    public Map<File, List<JsonObject>> loadFlightEventsFromFiles() throws IOException {
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
