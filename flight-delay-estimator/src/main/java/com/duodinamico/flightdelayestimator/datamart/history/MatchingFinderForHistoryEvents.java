package com.duodinamico.flightdelayestimator.datamart.history;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class MatchingFinderForHistoryEvents {

    FlightEventHistoryLoader flightEventHistoryLoader;
    WeatherEventHistoryLoader weatherEventHistoryLoader;

    public MatchingFinderForHistoryEvents(FlightEventHistoryLoader flightEventHistoryLoader, WeatherEventHistoryLoader weatherEventHistoryLoader) {
        this.flightEventHistoryLoader = flightEventHistoryLoader;
        this.weatherEventHistoryLoader = weatherEventHistoryLoader;
    }

    public List<Map<List<JsonObject>, List<JsonObject>>> findPossibleMatches() throws IOException, ParseException {
        Map<File, List<JsonObject>> flightHistory = this.flightEventHistoryLoader.loadFlightEventsFromFiles();
        Map<File, List<JsonObject>> weatherHistory = this.weatherEventHistoryLoader.loadWeatherEventsFromFiles();
        List<Map<List<JsonObject>, List<JsonObject>>> result = new ArrayList<>();
        for (File file : flightHistory.keySet()) {
            if (checkWeatherAvailability(file.getName(), weatherHistory)) {
                Optional<File> matchingFile = getMatchingFile(file, weatherHistory);
                if (matchingFile.isPresent()) {
                    Map<List<JsonObject>, List<JsonObject>> usefulEntry = new HashMap<>();
                    usefulEntry.put(flightHistory.get(file), weatherHistory.get(matchingFile.get()));
                    result.add(usefulEntry);
                }
            }
        }

        System.out.println("Ficheros de eventos históricos con matching disponible: " + result.size());
        return result;
    }

    public Optional<File> getMatchingFile (File file, Map<File, List<JsonObject>> weatherHistory) {
        String nextDateStr = getNextDayDate(file.getName());
        Optional<File> matchingFile = weatherHistory.keySet().stream()
                .filter(f -> f.getName().equals(nextDateStr))
                .findFirst();
        return matchingFile;
    }

    public boolean checkWeatherAvailability (String date, Map<File, List<JsonObject>> weatherHistory) throws ParseException {
        String nextDateStr = getNextDayDate(date);
        boolean exists = weatherHistory.keySet().stream()
                .anyMatch(file -> file.getName().equals(nextDateStr));
        return exists;
    }

    public String getNextDayDate (String date)  {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(date.substring(0, 8)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        calendar.add(Calendar.DATE, 1);
        return dateFormat.format(calendar.getTime())+".events";
    }
}
