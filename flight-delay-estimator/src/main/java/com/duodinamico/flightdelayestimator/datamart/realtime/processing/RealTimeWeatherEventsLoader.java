package com.duodinamico.flightdelayestimator.datamart.realtime.processing;

import com.duodinamico.flightdelayestimator.datamart.tools.WeatherEventDeserializer;
import com.duodinamico.openweathermapfeeder.domain.model.WeatherEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RealTimeWeatherEventsLoader {
    private final String datamartPartition;
    private final WeatherEventDeserializer deserializer = new WeatherEventDeserializer();

    public RealTimeWeatherEventsLoader(String datamartPartition) {
        this.datamartPartition = datamartPartition;
    }

    public Map<String, List<WeatherEvent>> loadWeatherEventsFromDatamartPartition() throws IOException {
        Map<String, List<WeatherEvent>> result = new HashMap<>();
        List<String> ids = getUniqueIds(this.datamartPartition);
        for (String id : ids) {
            List<WeatherEvent> weatherEvents = new ArrayList<>();
            for (String json : getJsonsById(this.datamartPartition,id)) { weatherEvents.add(this.deserializer.deserializeWeatherEvent(cleanEscapedJson(json))); }
            System.out.println(id);
            System.out.println(weatherEvents);
            result.put(id, weatherEvents);
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
