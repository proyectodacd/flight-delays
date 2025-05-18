package com.duodinamico.openweathermapfeeder.tools.converters;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UnixToUTCDateFormatter {

    public String formatUnixSecondsToISO8601UTC(int unixSeconds) {
        Instant instant = Instant.ofEpochSecond(unixSeconds);
        ZonedDateTime utcDateTime = instant.atZone(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return utcDateTime.format(formatter)+"+00:00";
    }

}


