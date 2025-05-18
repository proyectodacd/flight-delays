package com.duodinamico.flightdelayestimator.infrastructure.datamart.realtime.processing;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MatchingFinderForRealTimeEvents {
    RealTimeFlightEventsLoader flightEventsLoader;
    RealTimeWeatherEventsLoader weatherEventsLoader;

    public MatchingFinderForRealTimeEvents(RealTimeFlightEventsLoader realTimeFlightEventsLoader, RealTimeWeatherEventsLoader realTimeWeatherEventsLoader) {
        this.flightEventsLoader = realTimeFlightEventsLoader;
        this.weatherEventsLoader = realTimeWeatherEventsLoader;
    }

    public List<Map<List<JsonObject>, List<JsonObject>>> findPossibleMatchesForRealTimeEvents() throws IOException, ParseException {
        Map<String, List<JsonObject>> realTimeFlightHistory = this.flightEventsLoader.loadFlightEventsFromDatamartPartition();
        Map<String, List<JsonObject>> realTimeWeatherHistory = this.weatherEventsLoader.loadWeatherEventsFromDatamartPartition();
        List<Map<List<JsonObject>, List<JsonObject>>> result = new ArrayList<>();
        for (String key : realTimeFlightHistory.keySet()) {
            if (checkWeatherAvailability(key, realTimeWeatherHistory)) {
                Map<List<JsonObject>, List<JsonObject>> usefulEntry = new HashMap<>();
                usefulEntry.put(realTimeFlightHistory.get(key), realTimeWeatherHistory.get(getNextDayDate(key)));
                result.add(usefulEntry);
            }
        }

        String outputMessage = result.size() != 0 ? "\n\nConjuntos de eventos captados en tiempo real con matching disponible: " + result.size() + "\nActualizando Datamart a partir de eventos en tiempo real...\n" + "-----------------------------------------------------------------------" : "\nConjuntos de eventos captados en tiempo real con matching disponible: " + result.size();
        System.out.println(outputMessage);
        return result;
    }

    public boolean checkWeatherAvailability (String date, Map<String, List<JsonObject>> weatherHistory) throws ParseException {
        String nextDateStr = getNextDayDate(date);
        boolean exists = weatherHistory.keySet().stream()
                .anyMatch(dateReference -> dateReference.equals(nextDateStr));
        return exists;
    }

    public String getNextDayDate (String date)  {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        calendar.add(Calendar.DATE, 1);
        return dateFormat.format(calendar.getTime());
    }
}
