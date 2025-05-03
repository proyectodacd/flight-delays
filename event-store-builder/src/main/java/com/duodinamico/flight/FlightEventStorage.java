package com.duodinamico.flight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FlightEventStorage {

    private EventsFilePathGenerator filePathGenerator;

    public FlightEventStorage(EventsFilePathGenerator filePathGenerator) {
        this.filePathGenerator = filePathGenerator;
    }

    public void saveToEventsFile(String json) {
        try {
            String path = filePathGenerator.getEventFilePath(0);
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
