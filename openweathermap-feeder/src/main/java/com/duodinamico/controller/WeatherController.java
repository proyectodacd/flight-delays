package com.duodinamico.controller;

import com.duodinamico.controller.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.controller.eventintegration.WeatherEventSender;
import com.duodinamico.controller.persistency.AirportToCoordinates;
import com.duodinamico.infrastructure.adapters.sqlite.FlightSQLStore;
import com.duodinamico.controller.persistency.UnixConverter;
import com.duodinamico.controller.persistency.WeatherSQLStore;
import com.duodinamico.domain.model.FlightModel;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeatherController {
    private WeatherEventSender weatherEventSender;
    private FlightSQLStore flightSQLStore;
    private WeatherSQLStore weatherStore;
    private String apikey;
    private String coordinatesDoc;

    public WeatherController(String databasePath, String coordinatesDoc, String apiKey, String url) {
        this.weatherStore = new WeatherSQLStore(databasePath, coordinatesDoc, apiKey);
        this.flightSQLStore = new FlightSQLStore(databasePath);
        this.apikey = apiKey;
        this.coordinatesDoc = coordinatesDoc;
        this.weatherEventSender = new WeatherEventSender(url);
    }

    public void executeSQL() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable tarea2 = () -> {
            System.out.println("Ejecutando WeatherController a las: " + LocalDateTime.now());


            ArrayList<FlightModel> flightsList = flightSQLStore.loadFlights();
            for (FlightModel flight : flightsList) {
                try {
                    weatherStore.saveWeather(flight);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Clima de aeropuertos guardados.");
        };

        programarTarea(scheduler, tarea2, 14, 53);

        try {
            scheduler.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void executeEventSender() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        OpenWeatherMapProvider openWeatherMapProvider = new OpenWeatherMapProvider(apikey);
        AirportToCoordinates coordinates = new AirportToCoordinates(coordinatesDoc);
        UnixConverter unixConverter = new UnixConverter();

        Runnable tarea2 = () -> {
            System.out.println("Ejecutando WeatherController a las: " + LocalDateTime.now());


            ArrayList<FlightModel> flightsList = flightSQLStore.loadFlights();
            for (FlightModel flight : flightsList) {
                try {
                    WeatherEvent departureInfo = (coordinates.getAirportCoordinates(flight.getDepartureIata()) != null) ? openWeatherMapProvider.weatherEventProvider(coordinates.getAirportCoordinates(flight.getDepartureIata()),String.valueOf(unixConverter.convertToUnix(flight.getEstimatedDepartureTime(),flight.getDepartureTimezone()))) : null;
                    WeatherEvent arrivalInfo = (coordinates.getAirportCoordinates(flight.getArrivalIata()) != null) ? openWeatherMapProvider.weatherEventProvider(coordinates.getAirportCoordinates(flight.getArrivalIata()),String.valueOf(unixConverter.convertToUnix(flight.getEstimatedArrivalTime(),flight.getArrivalTimezone()))) : null;

                    weatherEventSender.sendWeatherEvents(departureInfo);
                    weatherEventSender.sendWeatherEvents(arrivalInfo);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Clima de vuelos guardados.");
        };

        programarTarea(scheduler, tarea2, 14, 57);

        try {
            scheduler.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void programarTarea(ScheduledExecutorService scheduler, Runnable tarea, int hora, int minuto) {
        long delay = calcularTiempoHasta(hora, minuto);
        System.out.println("Programando tarea para las " + hora + ":" + minuto + " con delay de " + delay + " segundos.");
        scheduler.scheduleAtFixedRate(tarea, delay, 12 * 3600, TimeUnit.SECONDS);
    }

    public static long calcularTiempoHasta(int hora, int minuto) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime proximo = ahora.withHour(hora).withMinute(minuto).withSecond(0);

        if (ahora.isAfter(proximo)) {
            proximo = proximo.plusDays(1);
        }

        return Duration.between(ahora, proximo).getSeconds();
    }

}


