package com.duodinamico.flightdelayestimator.datamart.realtime.processing;

import com.duodinamico.openweathermapfeeder.domain.model.WeatherEvent;
import com.duodinamico.aviationstackfeeder.domain.model.FlightEvent;

import java.io.File;
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

    public List<Map<List<FlightEvent>, List<WeatherEvent>>> findPossibleMatchesForRealTimeEvents() throws IOException, ParseException {
        Map<String, List<FlightEvent>> realTimeFlightHistory = this.flightEventsLoader.loadFlightEventsFromDatamartPartition();
        Map<String, List<WeatherEvent>> realTimeWeatherHistory = this.weatherEventsLoader.loadWeatherEventsFromDatamartPartition();
        List<Map<List<FlightEvent>, List<WeatherEvent>>> result = new ArrayList<>();
        for (String key : realTimeFlightHistory.keySet()) {
            System.out.println(getNextDayDate(key));
            if (checkWeatherAvailability(key, realTimeWeatherHistory)) {
                Map<List<FlightEvent>, List<WeatherEvent>> usefulEntry = new HashMap<>();
                usefulEntry.put(realTimeFlightHistory.get(key), realTimeWeatherHistory.get(getNextDayDate(key)));
                result.add(usefulEntry);
            }
        }

        String outputMessage = result.size() != 0 ? "\nConjuntos de eventos captados en tiempo real con matching disponible: " + result.size() + "\nActualizando Datamart a partir de eventos en tiempo real..." : "\nConjuntos de eventos captados en tiempo real con matching disponible: " + result.size();
        System.out.println(outputMessage);
        return result;
    }

    public boolean checkWeatherAvailability (String date, Map<String, List<WeatherEvent>> weatherHistory) throws ParseException {
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
