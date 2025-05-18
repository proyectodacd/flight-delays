package com.duodinamico.flightdelayestimator.datamart.realtime.processing;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RealTimeFlightEventsLoader {
    private final String datamartPartition;

    public RealTimeFlightEventsLoader(String datamartPartition) {
        this.datamartPartition = datamartPartition;
    }

    public Map<String, List<JsonObject>> loadFlightEventsFromDatamartPartition() throws IOException {
        Map<String, List<JsonObject>> result = new HashMap<>();
        List<String> ids = getUniqueIds(this.datamartPartition);
        for (String id : ids) {
            List<JsonObject> flightEvents = new ArrayList<>();
            for (String json : getJsonsById(this.datamartPartition,id)) {
                flightEvents.add(JsonParser.parseString(cleanEscapedJson(json)).getAsJsonObject()); }
            result.put(id, flightEvents);
        }
        return result;
    }

    public List<String> getUniqueIds(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.lines()
                    .skip(1)
                    .map(line -> line.split(",", 2)[0].trim())
                    .distinct()
                    .toList();
        }
    }

    public List<String> getJsonsById(String path, String targetId) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.lines()
                    .skip(1)
                    .map(line -> line.split(",", 2))
                    .filter(parts -> parts[0].trim().equals(targetId))
                    .map(parts -> parts[1].trim())
                    .toList();
        }
    }

    public String cleanEscapedJson(String jsonField) {
        return jsonField
                .replace("\"\"", "\"")
                .replaceAll("^\"|\"$", "");
    }

}
