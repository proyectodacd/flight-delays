package com.duodinamico.flight;

import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.domain.model.FlightEvent;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class EventsFilePathGeneratorForWriting {

    public String getFlightsFilePathForWriting(int delayDays, FlightEvent flightEvent) {
        String topic = "Flights";
        String subSegment = flightEvent.getSs();
        String formattedDate = DateTimeFormatter.ofPattern("yyyyMMdd").format(Instant.parse(flightEvent.getTs()).atZone(ZoneId.of("UTC")));

        return String.format("eventstore/%s/%s/%s.events", topic, subSegment, formattedDate);
    }

    public String getWeatherFilePathForWriting(int delayDays, WeatherEvent weatherEvent) {
        String topic = "Weather";
        String subSegment = weatherEvent.getSs();
        String formattedDate = DateTimeFormatter.ofPattern("yyyyMMdd").format(Instant.parse(weatherEvent.getTs()).atZone(ZoneId.of("UTC")));

        return String.format("eventstore/%s/%s/%s.events", topic, subSegment, formattedDate);
    }
}
