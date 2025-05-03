package com.duodinamico.infrastructure.adapters.activemq;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventsFilePathGenerator {

    public String getEventFilePath(int delayDays) {
        String topic = "Flights";
        String subSegment = "AviationStackFeeder";
        String date = LocalDate.now().minusDays(delayDays).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        return String.format("eventstore/%s/%s/%s.events", topic, subSegment, date);
    }
}
