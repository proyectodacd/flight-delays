package com.duodinamico.openweathermapfeeder.tools.converters;

import java.time.*;

public class UnixUtils {

    public String findUnixOfYesterday() {
        LocalDateTime yesterdayMidnightUTC = LocalDate.now(ZoneOffset.UTC).minusDays(1).atStartOfDay();
        long epochSeconds = yesterdayMidnightUTC.toEpochSecond(ZoneOffset.UTC);
        return String.valueOf(epochSeconds);

    }
}

