package com.duodinamico.infrastructure.rest.controller;


import com.duodinamico.controller.TaskScheduler;
import com.duodinamico.infrastructure.adapters.apiconsumer.AviationStackProvider;
import com.duodinamico.domain.ports.FlightStore;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FlightController {
    private AviationStackProvider aviationStackProvider;
    private FlightStore flightStore;
    private TaskScheduler taskScheduler;

    public FlightController(AviationStackProvider aviationStackProvider, FlightStore flightStore,
                            TaskScheduler taskScheduler) {
        this.aviationStackProvider = aviationStackProvider;
        this.taskScheduler = taskScheduler;
        this.flightStore = flightStore;
    }

    public void execute() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable tarea = () -> {
            System.out.println("Ejecutando FlightController a las: " + LocalDateTime.now());
            flightStore.saveFlights(aviationStackProvider.flightProvider());
            System.out.println("Vuelos guardados.");
        };

        this.taskScheduler.programarTarea(scheduler, tarea, 19, 16);
        this.taskScheduler.programarTarea(scheduler, tarea, 23, 32);

        try {
            scheduler.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
