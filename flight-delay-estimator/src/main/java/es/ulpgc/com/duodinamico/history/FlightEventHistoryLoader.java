package es.ulpgc.com.duodinamico.history;

import com.duodinamico.domain.model.FlightEvent;
import com.duodinamico.infrastructure.adapters.activemq.FlightEventDeserializer;

import java.io.*;
import java.util.*;

public class FlightEventHistoryLoader {

    private final File directory;
    private final FlightEventDeserializer deserializer = new FlightEventDeserializer();

    public FlightEventHistoryLoader(String directoryPath) {
        this.directory = new File(directoryPath);
    }

    public Map<File, List<FlightEvent>> loadFlightEventsFromFiles() throws IOException {
        Map<File, List<FlightEvent>> result = new HashMap<>();

        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Directorio no válido: " + directory.getAbsolutePath());
        }

        File[] files = directory.listFiles((dir, name) -> name.endsWith(".events"));
        if (files == null) return result;

        for (File file : files) {
            List<FlightEvent> events = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    FlightEvent event = deserializer.deserializeFlightEvent(line);
                    events.add(event);
                }
            }
            result.put(file, events);
        }
        return result;
    }
}
