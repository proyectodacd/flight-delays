package com.duodinamico.flightdelayestimator.datamart.tools;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {
    public void programarTareaCadaCiertoTiempo(ScheduledExecutorService scheduler, Runnable tarea, int frecuencia) {
        scheduler.scheduleAtFixedRate(tarea, 0, frecuencia, TimeUnit.SECONDS);
    }
}
