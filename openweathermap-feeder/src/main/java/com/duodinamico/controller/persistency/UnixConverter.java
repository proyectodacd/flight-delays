package com.duodinamico.controller.persistency;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UnixConverter {
    public int convertToUnix(String dateTime, String timeZone) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);

            ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(timeZone));

            return (int) (zonedDateTime.toEpochSecond()+600);
        } catch (DateTimeException e) {
            System.out.println("Error: " + e.getMessage());
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now().minusDays(1).toString());
    }
}
