package com.duodinamico.controller;

import com.duodinamico.controller.persistency.FlightSQLStore;
import com.duodinamico.controller.persistency.WeatherSQLStore;
import com.duodinamico.controller.model.FlightModel;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeatherController {
    private FlightSQLStore flightSQLStore;
    private WeatherSQLStore weatherStore;

    public WeatherController(String databasePath, String coordinatesDoc, String apiKey) {
        this.weatherStore = new WeatherSQLStore(databasePath, coordinatesDoc, apiKey);
        this.flightSQLStore = new FlightSQLStore(databasePath);
    }

    public void execute() {
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

        programarTarea(scheduler, tarea2, 12, 0);

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


