package com.duodinamico.flightdelayestimator.datamart.tools;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {
    public void programarTareaCadaCiertoTiempo(ScheduledExecutorService scheduler, Runnable tarea, int frecuencia, int delay) {
        scheduler.scheduleAtFixedRate(tarea, delay, frecuencia, TimeUnit.SECONDS);
    }
}
