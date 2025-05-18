package com.duodinamico.eventstorebuilder.application.usecases.eventstorebuildermanager;

import com.google.gson.JsonObject;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class EventsFilePathGenerator {

    public String getFlightsFilePathForWriting(JsonObject event) {
        String topic = "Flights";
        String subSegment = event.get("ss").getAsString();
        String formattedDate = DateTimeFormatter.ofPattern("yyyyMMdd").format(Instant.parse(event.get("ts").getAsString()).atZone(ZoneId.of("UTC")));

        return String.format("eventstore/%s/%s/%s.events", topic, subSegment, formattedDate);
    }

    public String getWeatherFilePathForWriting(JsonObject event) {
        String topic = "Weather";
        String subSegment = event.get("ss").getAsString();
        String formattedDate = DateTimeFormatter.ofPattern("yyyyMMdd").format(Instant.parse(event.get("ts").getAsString()).atZone(ZoneId.of("UTC")));

        return String.format("eventstore/%s/%s/%s.events", topic, subSegment, formattedDate);
    }
}
