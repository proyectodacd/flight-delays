package com.duodinamico.flight;

import com.duodinamico.domain.model.FlightEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FlightEventStorage {

    private EventsFilePathGeneratorForWriting filePathGenerator;

    public FlightEventStorage(EventsFilePathGeneratorForWriting filePathGenerator) {
        this.filePathGenerator = filePathGenerator;
    }

    public void saveFlightsToEventsFile(String json, FlightEvent event) {
        try {
            String path = filePathGenerator.getFlightsFilePathForWriting(0, event);
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
