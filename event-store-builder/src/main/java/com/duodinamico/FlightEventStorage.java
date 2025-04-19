package com.duodinamico;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FlightEventStorage {

    public String getEventFilePath() {
        String topic = "Flights";
        String subSegment = "AviationStackFeeder";
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        return String.format("eventstore/%s/%s/%s.events", topic, subSegment, date);
    }

    public void saveToEventsFile(String json) {
        try {
            String path = getEventFilePath();
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
