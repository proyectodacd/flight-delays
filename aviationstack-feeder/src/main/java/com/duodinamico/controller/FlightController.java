package com.duodinamico.controller;


import com.duodinamico.controller.apiconsumer.AviationStackProvider;
import com.duodinamico.controller.persistency.FlightSQLStore;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FlightController {
    private AviationStackProvider aviationStackProvider;
    private FlightSQLStore flightSQLStore;

    public FlightController(String[] apiKeys, String databasePath) {
        this.aviationStackProvider = new AviationStackProvider(apiKeys);
        this.flightSQLStore = new FlightSQLStore(databasePath);
    }

    public void execute() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable tarea = () -> {
            System.out.println("Ejecutando FlightController a las: " + LocalDateTime.now());
            flightSQLStore.saveFlights(aviationStackProvider.flightProvider());
            System.out.println("Vuelos guardados.");
        };

        programarTarea(scheduler, tarea, 12, 1);
        programarTarea(scheduler, tarea, 19, 35);

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
