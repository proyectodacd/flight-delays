package com.duodinamico.flightdelayestimator.tools;

import java.time.*;

public class UnixConverter {
    public int convertToUnix(String dateTime) {
        try {
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateTime);
            return (int) (offsetDateTime.toEpochSecond());
        } catch (DateTimeException e) {
            System.out.println("Error: " + e.getMessage());
            return -1;
        }
    }
}

