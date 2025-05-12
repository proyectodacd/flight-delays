package es.ulpgc.com.duodinamico.history;

import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.controller.eventintegration.WeatherEventDeserializer;

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
    private final WeatherEventDeserializer deserializer = new WeatherEventDeserializer();

    public WeatherEventHistoryLoader(String directoryPath) {
        this.directory = new File(directoryPath);
    }

    public Map<File, List<WeatherEvent>> loadWeatherEventsFromFiles() throws IOException {
        Map<File, List<WeatherEvent>> result = new HashMap<>();

        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Directorio no válido: " + directory.getAbsolutePath());
        }

        File[] files = directory.listFiles((dir, name) -> name.endsWith(".events"));
        if (files == null) return result;

        for (File file : files) {
            List<WeatherEvent> events = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    WeatherEvent event = deserializer.deserializeWeatherEvent(line);
                    events.add(event);
                }
            }
            result.put(file, events);
        }
        return result;
    }
}

