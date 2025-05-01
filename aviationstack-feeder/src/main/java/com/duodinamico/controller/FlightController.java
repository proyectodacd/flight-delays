package com.duodinamico.controller;


import com.duodinamico.controller.apiconsumer.AviationStackProvider;
import com.duodinamico.controller.eventIntegration.FlightEventSender;
import com.duodinamico.controller.persistency.FlightSQLStore;
import jakarta.jms.JMSException;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FlightController {
    private AviationStackProvider aviationStackProvider;
    private FlightEventSender flightEventSender;
    private FlightSQLStore flightSQLStore;
    private TaskScheduler taskScheduler;

    public FlightController(String[] apiKeys, String databasePath, String url) {
        this.aviationStackProvider = new AviationStackProvider(apiKeys);
        this.flightSQLStore = new FlightSQLStore(databasePath);
        this.taskScheduler = new TaskScheduler();
        this.flightEventSender = new FlightEventSender(url);
    }

    public void executeSQL() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable tarea = () -> {
            System.out.println("Ejecutando FlightController a las: " + LocalDateTime.now());
            flightSQLStore.saveFlights(aviationStackProvider.flightProvider());
            System.out.println("Vuelos guardados.");
        };

        this.taskScheduler.programarTarea(scheduler, tarea, 14, 8);
        this.taskScheduler.programarTarea(scheduler, tarea, 23, 32);

        try {
            scheduler.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void executeEventSender() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable tarea = () -> {
            System.out.println("Enviando mensajes de vuelos a las: " + LocalDateTime.now());
            flightEventSender.sendFlightEvents(aviationStackProvider.flightEventProvider());
            System.out.println("Mensajes de vuelos enviados.");
        };

        this.taskScheduler.programarTarea(scheduler, tarea, 14, 12);
        this.taskScheduler.programarTarea(scheduler, tarea, 23, 32);

        try {
            scheduler.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
