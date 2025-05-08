package com.duodinamico.controller.persistency;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.time.*;
import java.time.format.DateTimeParseException;

public class UnixConverter {
    public int convertToUnix(String dateTime) {
        try {
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateTime);
            return (int) (offsetDateTime.toEpochSecond() + 600);
        } catch (DateTimeException e) {
            System.out.println("Error: " + e.getMessage());
            return -1;
        }
    }
}

