package com.duodinamico.eventstorebuilder.application.usecases.eventstorebuildermanager;

import com.duodinamico.openweathermapfeeder.domain.model.WeatherEvent;
import com.duodinamico.aviationstackfeeder.domain.model.FlightEvent;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class EventsFilePathGenerator {

    public String getFlightsFilePathForWriting(FlightEvent flightEvent) {
        String topic = "Flights";
        String subSegment = flightEvent.getSs();
        String formattedDate = DateTimeFormatter.ofPattern("yyyyMMdd").format(Instant.parse(flightEvent.getTs()).atZone(ZoneId.of("UTC")));

        return String.format("eventstore/%s/%s/%s.events", topic, subSegment, formattedDate);
    }

    public String getWeatherFilePathForWriting(WeatherEvent weatherEvent) {
        String topic = "Weather";
        String subSegment = weatherEvent.getSs();
        String formattedDate = DateTimeFormatter.ofPattern("yyyyMMdd").format(Instant.parse(weatherEvent.getTs()).atZone(ZoneId.of("UTC")));

        return String.format("eventstore/%s/%s/%s.events", topic, subSegment, formattedDate);
    }
}
